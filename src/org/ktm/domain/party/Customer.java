package org.ktm.domain.party;

import javax.persistence.Entity;

@Entity
public class Customer extends PartyRole {

    private static final long serialVersionUID = 1L;

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
