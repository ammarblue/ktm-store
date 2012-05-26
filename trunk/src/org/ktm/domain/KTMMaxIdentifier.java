package org.ktm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "max_identifier")
public class KTMMaxIdentifier extends KTMEntity {

    private static final long serialVersionUID = 1L;
    
    private Integer uniqueId;
    private Integer version;
    private Integer maxPurchaseOrder;
    private Integer maxSalesOrder;

    public KTMMaxIdentifier() {
        maxPurchaseOrder = 1;
        maxSalesOrder = 1;
    }
    
    @Id
    @GeneratedValue
    @Column(name = "uniqueId", nullable = false)
    public Integer getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(Integer uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Version
    @Column(name = "version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Column(name="maxPurchaseOrde")
    public Integer getMaxPurchaseOrder() {
        return maxPurchaseOrder;
    }

    public void setMaxPurchaseOrder(Integer maxPurchaseOrder) {
        this.maxPurchaseOrder = maxPurchaseOrder;
    }

    @Column(name="maxSalesOrde")
    public Integer getMaxSalesOrder() {
        return maxSalesOrder;
    }

    public void setMaxSalesOrder(Integer maxSalesOrder) {
        this.maxSalesOrder = maxSalesOrder;
    }

}
