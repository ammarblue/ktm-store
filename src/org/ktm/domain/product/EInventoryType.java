package org.ktm.domain.product;

public enum EInventoryType {
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
}
