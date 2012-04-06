package org.ktm.domain.quantity;

import org.ktm.domain.KTMEntity;

public class Quantity extends KTMEntity {

    private static final long serialVersionUID = 1L;

    private Integer           uniqueId;
    private Integer           version;
    protected Double          amount;
    private Metric            metric;

    public Integer getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(Integer uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Quantity(Double amount, Metric metric) {
        this.amount = amount;
        this.metric = metric;
    }

    public Double getAmount() {
        return amount;
    }

    public Metric getMetric() {
        return metric;
    }

    public Quantity add(Quantity quantity) throws QuantityException {
        // must be the same Metric
        if (getMetric().equals(quantity.getMetric())) {
            return new Quantity(this.amount + quantity.amount, metric);

        } else {
            throw new QuantityException("Quantity is not the same Metric !!");
        }
    }

    public Quantity subtract(Quantity quantity) throws QuantityException {
        // must be the same Metric
        if (getMetric().equals(quantity.getMetric())) {
            return new Quantity(this.amount - quantity.amount, metric);

        } else {
            throw new QuantityException("Quantity is not the same Metric !!");
        }
    }

    public Quantity multiply(Double multiplier) {
        return new Quantity(amount * multiplier, metric);
    }

    public Quantity mulitply(Quantity quantity) {
        return new Quantity(amount * quantity.amount, metric);
    }

    public Quantity divide(Double divisor) {
        return new Quantity(amount / divisor, metric);
    }

    public Quantity divide(Quantity quantity) {
        return new Quantity(amount / quantity.amount, metric);
    }

    public void round(RoundingPolicy policy) {

    }

    public boolean equalTo(Quantity quantity) {
        return false;
    }

    public boolean greaterThan(Quantity quantity) {
        return false;
    }

    public boolean lessThan(Quantity quantity) {
        return false;
    }
}
