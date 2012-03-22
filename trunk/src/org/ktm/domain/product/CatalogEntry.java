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
 * The CatalogEntry represents information about a specific type of product held
 * in a ProductCatalog
 */
@Entity
public class CatalogEntry extends KTMEntity {

    private static final long serialVersionUID = -8647167825792048264L;

    private Integer           uniqueId;
    private Integer           version;
    private String            descripton;
    private ProductCatalog    productCatalog;
    private Set<ProductType>  productType      = new HashSet<ProductType>();

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

    @OneToMany(mappedBy = "catalogEntry", cascade = CascadeType.ALL)
    public Set<ProductType> getProductType() {
        return productType;
    }

    public void setProductType(Set<ProductType> productType) {
        this.productType = productType;
    }

    @ManyToOne
    public ProductCatalog getProductCatalog() {
        return productCatalog;
    }

    public void setProductCatalog(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
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
