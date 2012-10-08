package org.ktm.domain.money;

public enum EPaymentPolicy {
    CREDIT("payment_policy.credit"), NOCREDIT("payment_policy.no_credit");

    private String type;

    private EPaymentPolicy(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
