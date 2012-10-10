package org.ktm.domain.product;

import javax.persistence.Entity;

/*
 * The ProductInventoryEntry represents an InventoryEntry that holds a set of
 * ProductInstances all of the same ProductType.
 */
@Entity
public class ProductInventoryEntry extends InventoryEntry {

    private static final long serialVersionUID = 1L;

}
