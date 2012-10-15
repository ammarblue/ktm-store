package org.ktm.domain.money;

import javax.persistence.Embeddable;
import org.ktm.quantity.Metric;
import org.ktm.quantity.Quantity;
import org.ktm.quantity.SystemOfUnits;

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

}
