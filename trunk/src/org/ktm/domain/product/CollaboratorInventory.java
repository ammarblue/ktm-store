package org.ktm.domain.product;

import javax.persistence.ManyToOne;
import org.ktm.domain.party.Collaborator;

public class CollaboratorInventory extends Inventory {

    private static final long serialVersionUID = 1L;

    private Collaborator      parther;

    @ManyToOne
    public Collaborator getParther() {
        return parther;
    }

    public void setParther(Collaborator parther) {
        this.parther = parther;
    }
}
