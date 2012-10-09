package org.ktm.servlet;

public class FieldType {

    private String fieldName;
    private Object value;

    public FieldType(String fieldName, Object value) {
        this.setFieldName(fieldName);
        this.setValue(value);
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
