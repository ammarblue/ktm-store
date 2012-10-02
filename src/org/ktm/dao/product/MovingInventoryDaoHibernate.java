package org.ktm.dao.product;

import org.ktm.domain.product.MovingInventory;

public class MovingInventoryDaoHibernate extends InventoryDaoHibernate implements InventoryDao {

    private static final long serialVersionUID = 1L;

    @Override
    public Class<?> getFeaturedClass() {
        return MovingInventory.class;
    }

}