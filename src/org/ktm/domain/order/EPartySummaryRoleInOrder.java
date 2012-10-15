package org.ktm.domain.order;

import org.ktm.exception.KTMException;

public enum EPartySummaryRoleInOrder {
    VENDOR("role.vendor"), SALESAGENT("role.salesagent"), PAYMENTRECEIVER("role.payment_receiver"), ORDERINITATOR("role.order_initator"), PURCHASER("role.purchaser"), ORDERRECEIVER("role.orderreceiver"), ORDERLINERECEIVER("role.order_line_receiver");

    private String value;

    private EPartySummaryRoleInOrder(String value) {
        this.setValue(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public EPartySummaryRoleInOrder parse(String value) throws KTMException {
        return Enum.valueOf(EPartySummaryRoleInOrder.class, value);
    }

    @Override
    public String toString() {
        return value;
    }
}
