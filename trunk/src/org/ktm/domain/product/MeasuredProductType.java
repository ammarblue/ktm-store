package org.ktm.domain.product;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import org.ktm.domain.quantity.Metric;

@Entity
public class MeasuredProductType extends ProductType {

    private static final long serialVersionUID = 1L;

    private Metric            metric;

    @Embedded
    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }
}
