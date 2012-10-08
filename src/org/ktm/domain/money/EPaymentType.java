package org.ktm.domain.money;

public enum EPaymentType {
    Cash("payment.cash"), Check("payment.check"), CreditCard("payment.credit_card"), DebitCard("payment.debit_card");

    private String name;

    private EPaymentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
