package org.ktm.domain.product;

import org.ktm.core.EnumValues;

public enum EInventoryType implements EnumValues {
    Center("inventory.center"), SalePoint("inventory.sale_point"), Moving("inventory.moving");

    private String value;

    private EInventoryType(String value) {
        this.setValue(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String[] getValues() {
        String[] returnValues = new String[values().length];
        EInventoryType[] values = values();
        for (int i = 0; i < values.length; i++) {
            returnValues[i] = values[i].toString();
        }
        return returnValues;
    }
}
