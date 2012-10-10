package org.ktm.web.tabs.auth;

import java.io.Serializable;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.ktm.filter.AddableHttpRequest;
import org.ktm.utils.Localizer;

public class AuthenticatorFactory implements Serializable {

    private static final long   serialVersionUID   = 1L;
    private static Logger       log                = Logger.getLogger(AuthenticatorFactory.class);

    private final static String REQUEST_STORE_KEY  = "org.ktm.tag.auth.AuthenticatorFactory.REQUEST_STORE_KEY";
    private final static String DESTROYSESSION_KEY = "org.ktm.tag.auth.AuthenticatorFactory.DESTROYSESSION_KEY";

    public AuthenticatorFactory() {
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

    public static void saveRequestContext(HttpServletRequest request) {
        HttpSession session = request.getSession();
        RequestState requestState = new RequestState();

        String appContext = request.getContextPath();
        requestState.setAppContext(appContext);
        String servletPath = request.getServletPath();
        requestState.setModule(servletPath);

        String key;
        Map<String, String> parameters = new HashMap<String, String>();
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            key = names.nextElement();
            parameters.put(key, request.getParameter(key));
        }
        requestState.setParameters(parameters);
        requestState.setQueryString(request.getQueryString());

        session.setAttribute(REQUEST_STORE_KEY, requestState);
    }

    public static RequestState restoreRequestContext(HttpServletRequest request) {
        HttpSession session = request.getSession();
        RequestState requestState = null;

        try {
            requestState = (RequestState) session.getAttribute(REQUEST_STORE_KEY);
            if (requestState != null) {
                String key;
                Map<String, String> attributes = requestState.getParameters();
                Iterator<String> it = requestState.getParameters().keySet().iterator();
                while (it.hasNext()) {
                    key = it.next();
                    ((AddableHttpRequest) request).addParameter(key, attributes.get(key));
                }
            }
            session.removeAttribute(REQUEST_STORE_KEY);
        } catch (Exception e) {
            // do nothing
        }
        return requestState;
    }

    /*
     * public static String obtainForward(RequestState requestState) { String
     * result = ""; if (requestState != null && requestState.getModule() !=
     * null) { result = requestState.getModule(); } return result; }
     */

    public static String obtainForward(RequestState requestState) {
        String returnURL = "";
        if (requestState != null) {
            returnURL = requestState.getAppContext();
            if (requestState.getModule() != null) {
                log.info(requestState.getModule());
                if (requestState.getQueryString() != null) {
                    returnURL += requestState.getModule() + "?" + requestState.getQueryString();
                } else {
                    returnURL += requestState.getModule();
                }
            }
        }
        return returnURL;
    }

    public static boolean isUserInRoles(HttpServletRequest request, Collection<String> roles) {
        HttpSession session = request.getSession();
        Authenticator auth = (Authenticator) session.getAttribute(Authenticator.SESSION_CONTEXT_KEY);
        if (auth == null) {
            return false;
        }
        return auth.isInRoles(roles);
    }

    public static boolean isUserInAllRoles(HttpServletRequest request, Collection<String> roles) {
        HttpSession session = request.getSession();
        Authenticator auth = (Authenticator) session.getAttribute(Authenticator.SESSION_CONTEXT_KEY);
        if (auth == null) {
            return false;
        }

        return auth.isInAllRoles(roles);
    }

    public static boolean isUserLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Authenticator auth = (Authenticator) session.getAttribute(Authenticator.SESSION_CONTEXT_KEY);
        if (auth == null) {
            return false;
        }

        return auth.isUserLoggedIn();
    }

    public static boolean isUserLoggingOut(HttpServletRequest request) {
        Object object = request.getAttribute(DESTROYSESSION_KEY);
        if (object == null) {
            return false;
        }
        return true;
    }

    public static void setUserLoggingOut(HttpServletRequest request) {
        request.setAttribute(DESTROYSESSION_KEY, new Boolean(true));
    }

    public static Authenticator getAuthComponent(HttpServletRequest request, ServletContext servletContext, String authenticatorClassName) throws AuthException {
        HttpSession session = request.getSession();

        Authenticator auth = (Authenticator) session.getAttribute(Authenticator.SESSION_CONTEXT_KEY);
        if (auth == null) {
            auth = createAuthComponent(authenticatorClassName);
            try {
                auth.initialize(servletContext);
            } catch (AuthException e) {
                e.printStackTrace();
            }
            session.setAttribute(Authenticator.SESSION_CONTEXT_KEY, auth);
        }
        return auth;
    }

    public static Authenticator getAuthComponentNoCreate(HttpSession session) throws AuthException {
        return (Authenticator) session.getAttribute(Authenticator.SESSION_CONTEXT_KEY);
    }

    @SuppressWarnings("rawtypes")
    public static Authenticator createAuthComponent(String authenticatorClassName) throws AuthException {

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
            throw new AuthException(Localizer.getString("ERR_AUTH_LoadAuthenticatorException"));
        }
        try {
            o = c.newInstance();
        } catch (Exception ex) {
            String errMsg = Localizer.getString("ERR_AUTH_InstantiateAuthenticatorException");
            throw new AuthException(errMsg + " " + ex.getClass() + ": " + ex.getMessage());
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
        private String              requestURI;
        private String              appContext;
        private Map<String, String> parameters;

        public String getModule() {
            return this.module;
        }

        public void setAppContext(String appContext) {
            this.appContext = appContext;
        }

        public String getAppContext() {
            return appContext;
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

        public String getRequestURI() {
            return this.requestURI;
        }

        public void setRequestURI(String requestURI) {
            this.requestURI = requestURI;
        }

        public Map<String, String> getParameters() {
            return this.parameters;
        }

        public void setParameters(Map<String, String> parameters) {
            this.parameters = parameters;
        }
    }
}
