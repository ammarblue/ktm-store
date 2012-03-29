package org.ktm.actions;

import java.io.Serializable;

public class ListValue implements Serializable {

    private static final long serialVersionUID = 1L;

    private String            key;
    private String            value;

    public ListValue(String key, String value) {
        this.setKey(key);
        this.setValue(value);
    }
    
    public ListValue(Integer key, String value) {
        this(String.valueOf(key), value);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
