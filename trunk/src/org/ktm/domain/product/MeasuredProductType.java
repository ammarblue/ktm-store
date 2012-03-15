package org.ktm.domain.product;

import org.ktm.domain.quantity.Metric;

public class MeasuredProductType extends ProductType {

    private static final long serialVersionUID = -1001101148550691330L;

    private Metric metric;

    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }
}
