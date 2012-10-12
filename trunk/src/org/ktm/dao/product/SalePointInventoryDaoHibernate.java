package org.ktm.dao.product;

import org.ktm.domain.product.SalePointInventory;

public class SalePointInventoryDaoHibernate extends InventoryDaoHibernate implements InventoryDao {

    private static final long serialVersionUID = 1L;

    @Override
    public Class<?> getFeaturedClass() {
        return SalePointInventory.class;
    }

}
