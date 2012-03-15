package org.ktm.domain.product;

import org.ktm.domain.quantity.Quantity;

public class MeasuredProductInstance extends ProductInstance {

    private static final long serialVersionUID = 2979942445993881415L;

    private Quantity quantity;

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }
    
}
