package org.ktm.domain.order;

public enum EOrderStatus {
    INITIALIZING("initializing"), OPEN("open"), CLOSED("closed"), CANCELLED("cancelled");

    private String status;

    private EOrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public EOrderStatus parse(String value) {
        return Enum.valueOf(EOrderStatus.class, value);
    }

    @Override
    public String toString() {
        return status;
    }
}
