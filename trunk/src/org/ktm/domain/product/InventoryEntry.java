package org.ktm.domain.product;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import org.ktm.domain.KTMEntity;

/*
 * The InventoryEntry records a type of good or service and the number
 * of instance of that good or service that are available.
 */
@Entity
public class InventoryEntry implements KTMEntity {

    private static final long    serialVersionUID = 1L;

    private Integer              uniqueId;
    private Integer              version;
    private String               descripton;
    private Inventory            inventory;
    private ProductType          productType;
    private Set<ProductInstance> productInstances = new HashSet<ProductInstance>();

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

    @Column(name = "description")
    public String getDescripton() {
        return descripton;
    }

    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }

    @ManyToOne
    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @ManyToOne
    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    @OneToMany(mappedBy = "inventoryEntry", cascade = CascadeType.ALL)
    public Set<ProductInstance> getProductInstances() {
        return productInstances;
    }

    public void setProductInstances(Set<ProductInstance> productInstances) {
        this.productInstances = productInstances;
    }
}
