package org.ktm.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PropertyUtils {

    public static String capitalize(String property) {
        return Character.toUpperCase(property.charAt(0)) + property.substring(1);
    }

    public static Object getProperty(Object bean, String property) {
        Object result = null;
        try {
            String[] names = property.split("\\.");
            Object obj = bean;
            if (names.length > 0) {
                for (String method : names) {
                    obj = _getProperty(obj, method);
                }
                result = obj;
            } else {
                result = _getProperty(obj, property);
            }
        } catch (Exception ex) {

        }
        return result;
    }

    private static Object _getProperty(Object bean, String property) {
        String methodName = "get" + capitalize(property);
        try {
            Method method = bean.getClass().getMethod(methodName, (Class<?>[]) null);
            try {
                return method.invoke(bean, (Object[]) null);
            } catch (IllegalArgumentException e) {

            } catch (IllegalAccessException e) {

            } catch (InvocationTargetException e) {

            }
        } catch (SecurityException e) {

        } catch (NoSuchMethodException e) {

        }

        return null;
    }

    private static Object _setProperty(Object bean, String property, Object value) {
        String methodName = "set" + capitalize(property);
        Method method = null;

        Class<?>[] paramClasses = { String.class };
        Object[] paramValues = { value };

        try {
            method = bean.getClass().getMethod(methodName, paramClasses);
        } catch (SecurityException e) {

        } catch (NoSuchMethodException e) {

        }
        if (method != null) {
            try {
                return method.invoke(bean, paramValues);
            } catch (IllegalArgumentException e) {

            } catch (IllegalAccessException e) {

            } catch (InvocationTargetException e) {

            }
        }
        return null;
    }

    public static boolean isEqual(Object beanValue, Object itemValue) {
        if (beanValue instanceof Integer) {
            return ((Integer) beanValue).equals(itemValue);
        } else if (beanValue instanceof String) {
            return ((String) beanValue).equals(itemValue);
        }
        return false;
    }

    public static void setProperty(Object bean, String property, Object value) {
        if (bean != null) {
            try {
                int idx = property.indexOf(".", 0);
                if (idx > 0) {
                    String objName = property.substring(0, idx);
                    String tmpProperty = property.substring(idx + 1);

                    Object obj = getProperty(bean, objName);
                    setProperty(obj, tmpProperty, value);
                } else {
                    _setProperty(bean, property, value);
                }
            } catch (Exception ex) {

            }
        }
    }

}
