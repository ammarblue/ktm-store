package org.ktm.domain.product;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "package_type")
@Inheritance(strategy=InheritanceType.JOINED)
public class PackageType extends ProductType {

	private static final long serialVersionUID = 1051794584014937364L;

	private Set<ProductType> components = new HashSet<ProductType>();

	@ManyToMany
	public Set<ProductType> getComponents() {
		return components;
	}

	public void setComponents(Set<ProductType> components) {
		this.components = components;
	}
}
