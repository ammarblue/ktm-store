package org.ktm.domain.product;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.ktm.domain.KTMEntity;

@Entity
@Table(name = "product_instance")
@Inheritance(strategy=InheritanceType.JOINED)
public class ProductInstance extends KTMEntity {

	private static final long serialVersionUID = 4297163700298706453L;
	
	private Integer uniqueId;
	private Integer version;
	private ProductType type;
	private SerialNumber serialNumber;

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
}
