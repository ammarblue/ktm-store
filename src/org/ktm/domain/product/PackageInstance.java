package org.ktm.domain.product;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/*
 * The PackageInstance represents a collection of one or more ProductInstances
 * sold together to increase the business benefit generated by the sale. A
 * PackageInstnce is a kind of ProductInstance.
 */
@Entity
public class PackageInstance extends ProductInstance {

    private static final long    serialVersionUID = 1L;

    private Set<ProductInstance> contents         = new HashSet<ProductInstance>();

    @ManyToMany
    public Set<ProductInstance> getContents() {
        return contents;
    }

    public void setContents(Set<ProductInstance> contents) {
        this.contents = contents;
    }
}
