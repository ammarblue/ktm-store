package org.ktm.stock.bean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FormBean {

    private String  uniqueId    = "";
    private String  method      = "";
    private Boolean isNewObject = true;

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

    public Boolean isNewObject() {
        return isNewObject;
    }

    public void setIsNewObject(Boolean isNewObject) {
        this.isNewObject = isNewObject;
    }

    public void reset() {
        Class<?> c = getClass();
        Method[] ms = c.getMethods();
        for (Method m : ms) {
            Object[] paramValues = { "" };

            try {
                m.invoke(this, paramValues);
            } catch (IllegalArgumentException e) {

            } catch (IllegalAccessException e) {

            } catch (InvocationTargetException e) {

            } catch (Exception e) {

            }
        }
    }

}