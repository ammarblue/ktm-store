package org.ktm.dao.product;

import org.ktm.domain.product.FixedInventory;

public class FixedInventoryDaoHibernate extends InventoryDaoHibernate {

    private static final long serialVersionUID = 1L;

    @Override
    public Class<?> getFeaturedClass() {
        return FixedInventory.class;
    }

}