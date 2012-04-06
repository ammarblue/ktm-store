package org.ktm.domain.order;

public enum EPartySummaryRoleInOrder {
    VENDOR(1), SALESAGENT(2), PAYMENTRECEIVER(3), ORDERINITATOR(4), PURCHASER(5), ORDERRECEIVER(6), ORDERLINERECEIVER(7);
    
    private Integer id;
    
    private EPartySummaryRoleInOrder(Integer id) {
        this.setId(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
