package org.ktm.domain.money;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.ktm.domain.quantity.Metric;
import org.ktm.domain.quantity.Quantity;
import org.ktm.domain.quantity.SystemOfUnits;

@Embeddable
public class Money extends Quantity {

    private static final long serialVersionUID = 1L;

    public Money() {
        super(0.0, SystemOfUnits.getInstance().BATH);
    }

    public Money(Double amount) {
        super(amount, SystemOfUnits.getInstance().BATH);
    }

    public Money(Double amount, Metric metric) {
        super(amount, metric);
    }

    @Override
    @Column(name = "amount")
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
