package org.ktm.domain.product;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import org.ktm.domain.KTMEntity;
import org.ktm.domain.money.Price;

@Entity
public class ProductInstance implements KTMEntity {

    private static final long serialVersionUID = 1L;

    private Integer           uniqueId;
    private Integer           version;
    private ProductType       type;
    private SerialNumber      serialNumber;
    private Price             price;
    private InventoryEntry    inventoryEntry;

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

    @ManyToOne
    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public SerialNumber getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(SerialNumber serialNumber) {
        this.serialNumber = serialNumber;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    @ManyToOne
    public InventoryEntry getInventoryEntry() {
        return inventoryEntry;
    }

    public void setInventoryEntry(InventoryEntry inventoryEntry) {
        this.inventoryEntry = inventoryEntry;
    }
}
