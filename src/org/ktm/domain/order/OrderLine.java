package org.ktm.domain.order;

import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import org.ktm.domain.KTMEntity;
import org.ktm.domain.money.Money;
import org.ktm.domain.product.ProductIdentifier;
import org.ktm.domain.product.SerialNumber;

@Entity
public class OrderLine extends KTMEntity {

    private static final long   serialVersionUID = 1L;

    private Integer             uniqueId;
    private Integer             version;
    private OrderLineIdentifier identifier;
    private ProductIdentifier   productType;
    private Set<SerialNumber>   serialNumber;
    private String              description;
    private String              comment;
    private Integer             numberOrdered;
    private Money               unitPrice;
    private Date                expectedDeliveryDate;
    private Order               order;

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
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public OrderLineIdentifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(OrderLineIdentifier identifier) {
        this.identifier = identifier;
    }

    @ManyToOne
    public ProductIdentifier getProductType() {
        return productType;
    }

    public void setProductType(ProductIdentifier productType) {
        this.productType = productType;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Embedded
    public Money getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Money unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Column
    public Date getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    @OneToMany(cascade = CascadeType.ALL)
    public Set<SerialNumber> getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Set<SerialNumber> serialNumber) {
        this.serialNumber = serialNumber;
    }

    @Column
    public Integer getNumberOrdered() {
        return numberOrdered;
    }

    public void setNumberOrdered(Integer numberOrdered) {
        this.numberOrdered = numberOrdered;
    }

    @ManyToOne
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}
