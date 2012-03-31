package org.ktm.dao.product;

import org.ktm.domain.product.*;

public class MovingInventoryDaoHibernate extends InventoryDaoHibernate implements InventoryDao {

    private static final long serialVersionUID = 8685358823453581488L;

    @Override
    public Class<?> getFeaturedClass() {
        return MovingInventory.class;
    }
    
}