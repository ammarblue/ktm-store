package org.ktm.dao.product;

import org.ktm.domain.product.VehicleInventory;

public class VehicleInventoryDaoHibernate extends InventoryDaoHibernate implements InventoryDao {

    private static final long serialVersionUID = 1L;

    @Override
    public Class<?> getFeaturedClass() {
        return VehicleInventory.class;
    }

}
