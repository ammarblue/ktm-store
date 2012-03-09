package org.ktm.domain.product;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "package_instance")
public class PackageInstance extends ProductInstance {

	private static final long serialVersionUID = -2488958859213643252L;

	private Set<ProductInstance> contens = new HashSet<ProductInstance>();

	@ManyToMany
	public Set<ProductInstance> getContens() {
		return contens;
	}

	public void setContens(Set<ProductInstance> contens) {
		this.contens = contens;
	}
}
