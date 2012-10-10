package org.ktm.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ktm.utils.PropertyUtils;
import org.ktm.web.bean.FormBean;

public class DispatchServlet extends SecureServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected final ActionForward processRequest(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = form.getMethod();
        String module = getModuleName();

        if (method != null && module != null) {
            if (!method.isEmpty() && !module.isEmpty()) {
                String methodName = method.toLowerCase() + PropertyUtils.capitalize(module);
                Class<?>[] paramClasses = { FormBean.class, HttpServletRequest.class, HttpServletResponse.class };
                Object[] paramValues = { form, request, response };

                Method actionMethod = null;
                try {
                    actionMethod = getClass().getMethod(methodName, paramClasses);
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }

                try {
                    return (ActionForward) actionMethod.invoke(this, paramValues);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private String getModuleName() {
        String className = getBeanClass();
        if (className != null && !className.isEmpty()) {
            int idx = className.lastIndexOf('.') + 1;
            className = className.substring(idx);
            className = className.replaceFirst("Bean", "");
            return className;
        }
        return "";
    }

}
