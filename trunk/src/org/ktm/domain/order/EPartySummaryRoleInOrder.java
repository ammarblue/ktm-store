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
        EPartySummaryRoleInOrder result = null;
        if (value != null && !value.isEmpty()) {
            for (int i = 0; i < values().length; i++) {
                if (value.equals(values()[i].toString())) { return values()[i]; }
            }
        } else {
            throw new KTMException("ERR_can_t_parse_enum_value");
        }
        return result;
    }

    @Override
    public String toString() {
        return value;
    }
}
