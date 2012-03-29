package org.ktm.domain.product;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class FixedInventory extends Inventory {

    private static final long serialVersionUID = 1L;

}
