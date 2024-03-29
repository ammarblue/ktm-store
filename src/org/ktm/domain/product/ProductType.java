package org.ktm.domain.product;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import org.ktm.domain.KTMEntity;
import org.ktm.domain.money.Price;

@Entity
public class ProductType implements KTMEntity {

    private static final long serialVersionUID = 1L;

    private Integer           uniqueId;
    private Integer           version;
    private String            name;
    private String            description;
    private ProductIdentifier identifier;
    private Double            costPrice;
    private Set<Price>        prices;
    private CatalogEntryType  cataloEntryType;

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

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public ProductIdentifier getIdentifier() {
        return identifier;
    }

    public void setIdentifier(ProductIdentifier identifier) {
        this.identifier = identifier;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    @OneToMany(mappedBy = "productType", cascade = CascadeType.ALL)
    public Set<Price> getPrices() {
        return prices;
    }

    public void setPrices(Set<Price> prices) {
        this.prices = prices;
    }

    @ManyToOne
    public CatalogEntryType getCataloEntryType() {
        return cataloEntryType;
    }

    public void setCataloEntryType(CatalogEntryType cataloEntryType) {
        this.cataloEntryType = cataloEntryType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((uniqueId == null) ? 0 : uniqueId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        ProductType other = (ProductType) obj;
        if (identifier == null) {
            if (other.identifier != null) return false;
        } else if (!identifier.equals(other.identifier)) return false;
        if (name == null) {
            if (other.name != null) return false;
        } else if (!name.equals(other.name)) return false;
        if (uniqueId == null) {
            if (other.uniqueId != null) return false;
        } else if (!uniqueId.equals(other.uniqueId)) return false;
        return true;
    }

}
