package org.ktm.domain.product;

import javax.persistence.Entity;
import javax.persistence.Table;

/*
 * The ProductInventoryEntry represents an InventoryEntry that holds a set of
 * ProductInstances all of the same ProductType.
 */
@Entity
@Table(name = "product_inventory_entry")
public class ProductInventoryEntry extends InventoryEntry {

    private static final long serialVersionUID = -2880651474813202859L;

}
