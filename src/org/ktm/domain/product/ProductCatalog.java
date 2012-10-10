package org.ktm.domain.product;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import org.ktm.domain.KTMEntity;

/*
 * The Inventory represents a collection of InventoryEntries held in stock by a
 * business
 */
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {
    "uniqueId", "identifier", "name"
    })
})
public class ProductCatalog extends KTMEntity {

    private static final long  serialVersionUID = 1L;

    private Integer            uniqueId;
    private Integer            version;
    private String             identifier;
    private String             name;
    private List<CatalogEntry> catalogEntry;

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

    @Column(name = "identifier")
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "productCatalog", cascade = CascadeType.ALL)
    public List<CatalogEntry> getCatalogEntry() {
        return catalogEntry;
    }

    public void setCatalogEntry(List<CatalogEntry> catalogEntry) {
        this.catalogEntry = catalogEntry;
    }
}
