package org.ktm.domain.money;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.ktm.quantity.Quantity;
import org.ktm.quantity.SystemOfUnits;

@Embeddable
public class Money implements Serializable {

    private Quantity          quantity;

    private static final long serialVersionUID = 1L;

    public Money() {
        quantity = new Quantity(0.0, SystemOfUnits.getInstance().BATH);
    }

    public Money(Double amount) {
        quantity = new Quantity(amount, SystemOfUnits.getInstance().BATH);
    }

    @Column(name = "amount")
    public Double getAmount() {
        return quantity.getAmount();
    }

    public void setAmount(Double amount) {
        this.quantity.setAmount(amount);
    }

    @Column(name = "unit_name")
    public String getUnitName() {
        return quantity.getMetric().getMetricName();
    }

    public void setUnitName(String unitName) {
        quantity.getMetric().setMetricName(unitName);
    }

    @Column(name = "unit_description")
    public String getUnitDescription() {
        return quantity.getMetric().getDefinition();
    }

    public void setUnitDescription(String definition) {
        quantity.getMetric().setDefinition(definition);
    }

    @Column(name = "unit_symbol")
    public String getUnitSymbol() {
        return quantity.getMetric().getSymbol();
    }

    public void setUnitSymbol(String symbol) {
        quantity.getMetric().setSymbol(symbol);
    }

}
