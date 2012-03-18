package org.ktm.domain.party;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/*
 * Organization represents an administrative and functional structure.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Organization extends Party {

    private static final long serialVersionUID = 6900931315594214549L;

    private String            name;

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
