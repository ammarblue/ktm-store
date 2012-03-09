package org.ktm.domain.product;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.ktm.domain.KTMEntity;

@Entity
@Table(name = "product_catalog")
public class ProductCatalog extends KTMEntity {

	private static final long serialVersionUID = -5079941812147428733L;
	
	private Integer uniqueId;
	private Integer version;
	private Set<CatalogEntry> catalogEntry = new HashSet<CatalogEntry>();

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

	@OneToMany
	public Set<CatalogEntry> getCatalogEntry() {
		return catalogEntry;
	}

	public void setCatalogEntry(Set<CatalogEntry> catalogEntry) {
		this.catalogEntry = catalogEntry;
	}
}
