package org.ktm.dao.product;

public enum EInventoryType {
    ALL("inventory.all"), CENTER("inventory.center"), SALE_POINT("inventory.sale_point"), MOVING("inventory.moving");

    private String type;

    private EInventoryType(String type) {
        this.setType(type);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

    public static EInventoryType parse(String type) {
        for (EInventoryType etype : EInventoryType.values()) {
            if (etype.toString().equals(type)) { return etype; }
        }
        return ALL;
    }
}
