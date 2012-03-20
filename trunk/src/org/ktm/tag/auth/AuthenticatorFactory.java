package org.ktm.tag.auth;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.ktm.actions.KTMAction;
import org.ktm.domain.party.Party;
import org.ktm.web.utils.Globals;
import com.opensymphony.xwork2.ActionContext;

public class AuthenticatorFactory implements Serializable {

    private static final long   serialVersionUID   = 1L;

    private final static String REQUEST_STORE_KEY  = "org.ktm.tag.auth.AuthenticatorFactory.REQUEST_STORE_KEY";
    private final static String DESTROYSESSION_KEY = "org.ktm.tag.auth.AuthenticatorFactory.DESTROYSESSION_KEY";

    public AuthenticatorFactory() {
    }

    private static Map<String, Object> getSession() throws AuthException {
        Map<String, Object> session = ActionContext.getContext().getSession();
        if (session == null) {
            throw new AuthException("No session object");
        }
        return session;
    }

    public static String getParameter(String queryString, String paramName) {
        String result = "";
        if (queryString != null) {
            int idx = queryString.indexOf(paramName);
            String tmp = queryString.substring(idx);
            idx = tmp.indexOf("=");
            if (idx >= 0) {
                int eid = tmp.indexOf("&");
                if (eid >= 0) {
                    result = tmp.substring(idx + 1, eid - (idx + 1));
                } else {
                    result = tmp.substring(idx + 1);
                }
            }
        }
        return result;
    }

    public static void saveRequestContext(HttpServletRequest request) throws AuthException {

        RequestState requestState = new RequestState();
        String queryString = (String) request.getAttribute(Globals.FORWARD_QUERY_STRING);

        if (queryString != null && queryString.indexOf("login") < 0) {
            requestState.setModule(getParameter(queryString, "page"));
            /*
             * String key; Map<Object,Object> attributes = new
             * HashMap<Object,Object>(); Enumeration<String> names =
             * request.getAttributeNames() ; while (names.hasMoreElements()) {
             * key = names.nextElement(); attributes.put(key,
             * request.getAttribute(key)) ; }
             * requestState.setAttributes(attributes);
             * requestState.setQueryString(request.getQueryString());
             */

            getSession().put(REQUEST_STORE_KEY, requestState);
        }
    }

    public static RequestState restoreRequestContext(Map<String,Object> request) {

        RequestState requestState = null;

        try {
            requestState = (RequestState) getSession().get(REQUEST_STORE_KEY);
            if (requestState != null) {
                /*
                 * String key; Map<Object,Object> attributes =
                 * requestState.getAttributes(); Iterator<Object> it =
                 * requestState.attributes.keySet().iterator();
                 * while(it.hasNext()) { key = (String) it.next();
                 * request.setAttribute(key, attributes.get(key)); }
                 */
            }
            getSession().remove(REQUEST_STORE_KEY);
        } catch (Exception e) {
            // do nothing
        }
        return requestState;
    }

    public static String obtainForward(RequestState requestState) {
        String result = "";
        if (requestState != null && requestState.getModule() != null) {
            result = requestState.getModule();
        }
        return result;
    }

    /*
     * public static String obtainForward(RequestState requestState) { String
     * returnURL = ""; if(requestState.getQueryString()!=null) { returnURL =
     * requestState.getRequestURI() + "?" + requestState.getQueryString(); }else
     * { returnURL = requestState.getRequestURI(); }
     * 
     * if (requestState.getModule() != null) {
     * //log.info(requestState.getModule()); } return returnURL; }
     */

    public static boolean isUserInRoles(Collection<String> roles) throws AuthException {
        Authenticator auth = (Authenticator) getSession().get(Authenticator.SESSION_CONTEXT_KEY);
        if (auth == null) {
            return false;
        }
        return auth.isInRoles(roles);
    }

    public static boolean isUserInAllRoles(Collection<String> roles) throws AuthException {
        Authenticator auth = (Authenticator) getSession().get(Authenticator.SESSION_CONTEXT_KEY);
        if (auth == null) {
            return false;
        }

        return auth.isInAllRoles(roles);
    }

    public static boolean isUserLoggedIn() throws AuthException {
        boolean result = false;
        Authenticator auth = (Authenticator) getSession().get(Authenticator.SESSION_CONTEXT_KEY);
        if (auth == null) {
            return false;
        }

        if (auth.isUserLoggedIn())  {
            getSession().put(DESTROYSESSION_KEY, null);
            result = true;
        }
        return result;
    }

    public static Party getParty() throws AuthException {
        Authenticator auth = (Authenticator) getSession().get(Authenticator.SESSION_CONTEXT_KEY);
        if (auth == null) {
            return null;
        }
        return auth.getParty();
    }

    public static boolean isUserLoggingOut() throws AuthException {
        Object object = getSession().get(DESTROYSESSION_KEY);
        if (object == null) {
            return false;
        }
        return true;
    }

    public static void setUserLoggingOut() throws AuthException {
        Authenticator auth = (Authenticator) getSession().get(Authenticator.SESSION_CONTEXT_KEY);
        if (auth != null) {
            try {
                auth.doLogout();
            } catch (AuthException e) {
                e.printStackTrace();
            }
        }
        getSession().put(DESTROYSESSION_KEY, new Boolean(true));
    }

    public static Authenticator getAuthComponent(KTMAction action, ServletContext servletContext, String authenticatorClassName) throws AuthException {
        Authenticator auth = (Authenticator) getSession().get(Authenticator.SESSION_CONTEXT_KEY);
        if (auth == null) {
            auth = createAuthComponent(action, authenticatorClassName);
            try {
                auth.initialize(servletContext);
            } catch (AuthException e) {
                e.printStackTrace();
            }
            getSession().put(Authenticator.SESSION_CONTEXT_KEY, auth);
        }
        return auth;
    }

    public static Authenticator getAuthComponentNoCreate() throws AuthException {
        Authenticator auth = (Authenticator) getSession().get(Authenticator.SESSION_CONTEXT_KEY);
        return auth;
    }

    @SuppressWarnings("rawtypes")
    public static Authenticator createAuthComponent(KTMAction action, String authenticatorClassName) throws AuthException {

        Class c = null;
        Object o;
        Authenticator auth = null;

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

            if (classLoader != null) {
                c = classLoader.loadClass(authenticatorClassName);
            } else {
                c = Class.forName(authenticatorClassName);
            }
        } catch (Exception ex) {
            throw new AuthException(action.getText("ERR_AUTH_LoadAuthenticatorException"));
        }
        try {
            o = c.newInstance();
        } catch (Exception ex) {
            throw new AuthException(action.getText("ERR_AUTH_InstantiateAuthenticatorException", ex.getClass() + ": " + ex.getMessage()));
        }
        try {
            auth = (Authenticator) o;
        } catch (Exception ex) {
            throw new AuthException("Authenticator class " + authenticatorClassName + "can not be cast to org.itsci.web.auth.Authenticator");
        }

        return auth;
    }

    public static class RequestState {
        private String              module;
        private String              queryString;
        // private String requestURI;
        private Map<Object, Object> attributes;

        public String getModule() {
            return this.module;
        }

        public void setModule(String module) {
            this.module = module;
        }

        public String getQueryString() {
            return this.queryString;
        }

        public void setQueryString(String queryString) {
            this.queryString = queryString;
        }

        // public String getRequestURI() {
        // return this.requestURI;
        // }

        // public void setRequestURI(String requestURI) {
        // this.requestURI = requestURI;
        // }

        public Map<Object, Object> getAttributes() {
            return this.attributes;
        }

        public void setAttributes(Map<Object, Object> attributes) {
            this.attributes = attributes;
        }
    }
}
