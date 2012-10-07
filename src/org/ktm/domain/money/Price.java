package org.ktm.domain.money;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import org.ktm.domain.KTMEntity;
import org.ktm.domain.product.ProductType;

@Entity
public class Price extends KTMEntity {

    private static final long serialVersionUID = 1L;

    private Integer           uniqueId;
    private Integer           version;
    private Money             amount;
    private Date              validFrom;
    private Date              validTo;
    private ProductType       productType;

    @Override
    @Id
    @GeneratedValue
    @Column(name = "uniqueId", nullable = false)
    public Integer getUniqueId() {
        return uniqueId;
    }

    @Override
    public void setUniqueId(Integer uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Override
    @Version
    @Column(name = "version")
    public Integer getVersion() {
        return version;
    }

    @Override
    public void setVersion(Integer version) {
        this.version = version;
    }

    @Embedded
    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    @Column(name = "valid_from")
    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    @Column(name = "valid_to")
    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    @ManyToOne
    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

}
