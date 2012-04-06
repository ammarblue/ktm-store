package org.ktm.domain.order;

public enum EOrderStatus {
    INITIALIZING(1), OPEN(2), CLOSED(3), CANCELLED(4);
    
    private Integer id;
    
    private EOrderStatus(Integer id) {
        this.setId(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
