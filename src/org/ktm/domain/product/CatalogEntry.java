package org.ktm.domain.product;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import org.ktm.domain.KTMEntity;

/*
 * The CatalogEntry represents information about a specific type of product held
 * in a ProductCatalog
 */
@Entity
public class CatalogEntry implements KTMEntity {

    private static final long serialVersionUID = 1L;

    private Integer           uniqueId;
    private Integer           version;
    private String            identifier;
    private String            description;
    private CatalogEntryType  type;
    private Set<ProductType>  productType      = new HashSet<ProductType>();

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

    @Column(name = "identifier")
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public void setVersion(Integer version) {
        this.version = version;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    public CatalogEntryType getType() {
        return type;
    }

    public void setType(CatalogEntryType type) {
        this.type = type;
    }

    @OneToMany
    public Set<ProductType> getProductType() {
        return productType;
    }

    public void setProductType(Set<ProductType> productType) {
        this.productType = productType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((uniqueId == null) ? 0 : uniqueId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        CatalogEntry other = (CatalogEntry) obj;
        if (uniqueId == null) {
            if (other.uniqueId != null) return false;
        } else if (!uniqueId.equals(other.uniqueId)) return false;
        return true;
    }

}
