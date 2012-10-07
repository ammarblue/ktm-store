package org.ktm.domain.money;

public enum EPaymentType {
    Cash("cash"), Check("check"), CreditCard("credit_card"), DebitCard("debit_card");

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
