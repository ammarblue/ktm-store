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

    public EPaymentPolicy parse(String value) {
        return Enum.valueOf(EPaymentPolicy.class, value);
    }

    @Override
    public String toString() {
        return type;
    }
}
