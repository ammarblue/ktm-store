package org.ktm.dao.product;

import org.ktm.domain.product.CenterInventory;

public class CenterInventoryDaoHibernate extends InventoryDaoHibernate implements InventoryDao {

    private static final long serialVersionUID = 1L;

    @Override
    public Class<?> getFeaturedClass() {
        return CenterInventory.class;
    }

}
