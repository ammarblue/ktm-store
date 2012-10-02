package org.ktm.servlet;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ktm.core.KTMContext;
import org.ktm.stock.bean.FormBean;
import org.ktm.utils.Globals;
import org.ktm.utils.PropertyUtils;

public abstract class AbstractServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private KTMContext        appContext;
    private FormBean          bean;

    public AbstractServlet() {
        super();
    }

    protected String getBeanClass() {
        return "org.ktm.stock.bean.FormBean";
    }

    private FormBean getBean() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (bean == null) {
            Class<?> c = null;

            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

            if (classLoader != null) {
                c = classLoader.loadClass(getBeanClass());
            } else {
                c = Class.forName(getBeanClass());
            }

            bean = (FormBean) c.newInstance();
        }
        return bean;
    }

    protected boolean prepareRequest(HttpServletRequest request) throws ServletException, IOException {
        ServletContext ctx = request.getServletContext();
        appContext = (KTMContext) ctx.getAttribute(KTMContext.APPCONTEXT);

        try {
            getBean().reset();

            Enumeration<String> em = request.getParameterNames();
            while (em.hasMoreElements()) {
                String paramName = em.nextElement();
                Object param = request.getParameter(paramName);
                PropertyUtils.setProperty(getBean(), paramName, param);
            }
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
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String loginUri = request.getServletContext().getInitParameter(Globals.LOGIN_PAGE);
            ActionForward action = ActionForward.getUri(this, request, loginUri);
            if (prepareRequest(request)) {
                action = processRequest(getBean(), request, response);
            }
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
            prepareRequest(request);
            ActionForward action = processRequest(getBean(), request, response);
            postRequest(request, response, action);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    protected abstract ActionForward processRequest(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

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

}
