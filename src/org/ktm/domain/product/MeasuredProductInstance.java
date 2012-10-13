package org.ktm.domain.product;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import org.ktm.quantity.Quantity;

@Entity
public class MeasuredProductInstance extends ProductInstance {

    private static final long serialVersionUID = 1L;

    private Quantity          quantity;

    @Embedded
    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

}
