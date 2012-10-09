package org.ktm.stock.bean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import org.ktm.domain.KTMEntity;
import org.ktm.servlet.AbstractServlet;
import org.ktm.utils.Localizer;

public abstract class FormBean {

    private String          uniqueId = "";
    private String          method   = "";
    private AbstractServlet servlet  = null;

    public FormBean getBeanInstance() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        FormBean bean = null;
        if (servlet != null) {
            Class<?> c = null;

            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

            if (classLoader != null) {
                c = classLoader.loadClass(servlet.getBeanClass());
            } else {
                c = Class.forName(servlet.getBeanClass());
            }

            bean = (FormBean) c.newInstance();
            bean.setServlet(servlet);
        }
        return bean;
    }

    public void loadToForm(KTMEntity entity) {
        if (entity != null) {
            this.setUniqueId(String.valueOf(entity.getUniqueId()));
        }
    }

    public void syncToEntity(KTMEntity entity) {
        if (entity != null) {
            try {
                entity.setUniqueId(Integer.valueOf(this.getUniqueId()));
            } catch (Exception e) {

            }
        }
    }

    public abstract void loadFormCollection(Collection<?> entitys);

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    private Object[] getDefaultParameters(Method m) {
        Class<?>[] clazzs = m.getParameterTypes();
        Object[] params = new Object[clazzs.length];

        for (int i = 0; i < clazzs.length; i++) {
            Class<?> clazz = clazzs[i];
            String className = Localizer.getClassName(clazz);
            if (className.equals("Integer") || className.equals("Double") || className.equals("Float") || className.equals("Byte") || className.equals("Long") || className.equals("Short") || className.equals("Number")) {
                params[i] = 0;
            } else if (className.equals("Boolean")) {
                params[i] = Boolean.valueOf("false");
            } else {
                params[i] = String.valueOf("");
            }
        }
        return params;
    }

    protected boolean isSkipReset(String methodName) {
        boolean result = false;
        // if (methodName != null) {
        // if (methodName.equals("setMethod")) {
        // result = true;
        // }
        // }
        return result;
    }

    public void reset() {
        Class<?> c = getClass();
        Method[] ms = c.getMethods();
        for (Method m : ms) {
            m.getParameterTypes();
            Object[] paramValues = getDefaultParameters(m);

            if (isSkipReset(m.getName())) {
                continue;
            }

            try {
                if (m.getName().startsWith("set")) {
                    m.invoke(this, paramValues);
                }
            } catch (IllegalArgumentException e) {

            } catch (IllegalAccessException e) {

            } catch (InvocationTargetException e) {

            } catch (Exception e) {

            }
        }
    }

    public AbstractServlet getServlet() {
        return servlet;
    }

    public void setServlet(AbstractServlet servlet) {
        this.servlet = servlet;
    }
}
