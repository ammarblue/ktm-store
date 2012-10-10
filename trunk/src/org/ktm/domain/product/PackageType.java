package org.ktm.domain.product;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/*
 * The PackageType specifies a set of component ProductTypes and roles about how
 * these may be combined to create PackageInstances. A PackgeType is a kind of
 * ProductType
 */
@Entity
public class PackageType extends ProductType {

    private static final long serialVersionUID = 1L;

    private Set<ProductType>  components       = new HashSet<ProductType>();

    @ManyToMany
    public Set<ProductType> getComponents() {
        return components;
    }

    public void setComponents(Set<ProductType> components) {
        this.components = components;
    }
}
