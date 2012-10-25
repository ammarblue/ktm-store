package org.ktm.domain.product;

import javax.persistence.Entity;

@Entity
public class MeasuredProductType extends ProductType {

    private static final long serialVersionUID = 1L;

    private String            unit;
    private Double            quantity;
    private Double            packAmount;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPackAmount() {
        return packAmount;
    }

    public void setPackAmount(Double packAmount) {
        this.packAmount = packAmount;
    }

}
