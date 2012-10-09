package org.ktm.servlet;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ktm.core.KTMContext;
import org.ktm.stock.bean.FormBean;
import org.ktm.utils.Globals;
import org.ktm.utils.Localizer;
import org.ktm.utils.PropertyUtils;

public abstract class AbstractServlet extends HttpServlet {
    private static final long            serialVersionUID = 1L;

    private KTMContext                   appContext;
    private FormBean                     bean;
    private final Map<String, FieldType> paramMap         = new HashMap<String, FieldType>();
    private final Vector<String>         paramKey         = new Vector<String>();

    public AbstractServlet() {
        super();
    }

    protected abstract ActionForward processRequest(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    public String getBeanClass() {
        return "org.ktm.stock.bean.FormBean";
    }

    public FormBean getBean() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (bean == null) {
            Class<?> c = null;

            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

            if (classLoader != null) {
                c = classLoader.loadClass(getBeanClass());
            } else {
                c = Class.forName(getBeanClass());
            }

            bean = (FormBean) c.newInstance();
            bean.setServlet(this);
        }
        return bean;
    }

    private Object getFieldTypeObject(FieldType type, String value) {
        Object result = null;
        if (type.getValue() != null && type.getValue() instanceof Integer) {
            result = Integer.parseInt(value == null ? "0" : value);
        } else if (type.getValue() != null && type.getValue() instanceof Long) {
            result = Long.parseLong(value == null ? "0" : value);
        } else if (type.getValue() != null && type.getValue() instanceof Boolean) {
            result = Boolean.parseBoolean(value == null ? "0" : value);
        } else if (type.getValue() != null && type.getValue() instanceof Short) {
            result = Short.parseShort(value == null ? "0" : value);
        } // else if() {} // TODO: more type support
        else {
            result = value == null ? "" : String.valueOf(value);
        }
        return result;
    }

    private void performLoadParameter(Map<String, FieldType> paramMap, HttpServletRequest request, FormBean bean) {
        Iterator<String> it = paramKey.iterator();
        while (it.hasNext()) {
            String paramName = it.next();
            FieldType type = paramMap.get(paramName);
            String svalue = request.getParameter(paramName);
            Object obj = getFieldTypeObject(type, svalue);
            PropertyUtils.setProperty(bean, type.getFieldName(), obj);
        }
    }

    private void performAddFieldName(FormBean bean, String prefix, Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            Object obj = PropertyUtils.getProperty(bean, fieldName);
            if (obj instanceof FormBean) {
                performAddFieldName((FormBean) obj, fieldName, obj.getClass());
            } else {
                if (!prefix.isEmpty()) {
                    fieldName = prefix + "." + fieldName;
                }
                if (!paramMap.containsKey(fieldName)) {
                    paramMap.put(fieldName, new FieldType(fieldName, obj));
                    paramKey.add(fieldName);
                }
            }
        }

        if (!Localizer.getClassName(clazz.getSuperclass()).equals("Object")) {
            performAddFieldName(bean, prefix, clazz.getSuperclass());
        }
    }

    private Map<String, FieldType> collectAllParamName(HttpServletRequest request, FormBean bean) {
        paramMap.clear();
        paramKey.clear();

        performAddFieldName(bean, "", bean.getClass());

        return paramMap;
    }

    protected boolean prepareRequest(HttpServletRequest request) throws ServletException, IOException {
        ServletContext ctx = request.getServletContext();
        appContext = (KTMContext) ctx.getAttribute(KTMContext.APPCONTEXT);

        try {
            FormBean bean = getBean();
            bean.reset();
            performLoadParameter(collectAllParamName(request, bean), request, bean);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return true;
    }

    protected void postRequest(HttpServletRequest request, HttpServletResponse response, ActionForward action) throws ServletException, IOException {
        try {
            request.setAttribute("bean", getBean());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (action != null) {
            String uri = action.getForwardUri();
            while (uri.indexOf("//") > 0) {
                uri = uri.replaceFirst("//", "/");
            }
            if (action.isRedirect()) {
                response.sendRedirect(uri);
            } else {
                request.getRequestDispatcher(uri).forward(request, response);
            }

            if (action.isEndConversation()) {
                closeSession(request);
            }
        }
    }

    private ActionForward checkLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String loginUri = request.getServletContext().getInitParameter(Globals.LOGIN_PAGE);
        ActionForward action = ActionForward.getUri(this, request, loginUri);
        if (prepareRequest(request)) {
            action = processRequest(getBean(), request, response);
        }
        return action;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ActionForward action = checkLogin(request, response);
            postRequest(request, response, action);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ActionForward action = checkLogin(request, response);
            postRequest(request, response, action);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public KTMContext getAppContext() {
        return appContext;
    }

    protected boolean isEmptyString(String str) {
        if (str == null)
            return true;
        if (str.trim().isEmpty())
            return true;
        return false;
    }

    protected String getBasePath(HttpServletRequest request) {
        return request.getServletContext().getInitParameter(Globals.BASE_PATH);
    }

    protected void closeSession(HttpServletRequest request) {
        request.setAttribute(Globals.ENTITY_SESSION_END_OF_CONVERSATION, Globals.ANY);
    }

}
