package org.ktm.domain.product;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.ktm.domain.KTMEntity;

/*
 * The CatalogEntry represents information about a specific type of
 * product held in a ProductCatalog
 */
@Entity
@Table(name = "catalog_entry")
public class CatalogEntry extends KTMEntity {

	private static final long serialVersionUID = -8647167825792048264L;
	
	private Integer uniqueId;
	private Integer version;
	private String catalogIdentifier;
	private String descripton;
	private String category; // or a supplier
	private Set<ProductType> productType = new HashSet<ProductType>();

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
	@Column(name="version")
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

    @Column(name="catalogIdentifier")
    public String getCatalogIdentifier() {
        return catalogIdentifier;
    }

    public void setCatalogIdentifier(String catalogIdentifier) {
        this.catalogIdentifier = catalogIdentifier;
    }

	@Column(name="description")
	public String getDescripton() {
		return descripton;
	}

	public void setDescripton(String descripton) {
		this.descripton = descripton;
	}

    @Column(name="category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "catalogentry_producttype", joinColumns = { @JoinColumn(name = "catalogEntry_fk") }, inverseJoinColumns = { @JoinColumn(name = "productType_kf") })
	public Set<ProductType> getProductType() {
		return productType;
	}

	public void setProductType(Set<ProductType> productType) {
		this.productType = productType;
	}
}
