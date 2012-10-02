package org.ktm.dao.product;

import java.util.Set;
import org.ktm.dao.AbstractHibernateStorageDao;
import org.ktm.domain.product.Inventory;
import org.ktm.domain.product.ProductIdentifier;
import org.ktm.domain.product.ProductType;

public class InventoryDaoHibernate extends AbstractHibernateStorageDao implements InventoryDao {

    private static final long serialVersionUID = 1L;

    @Override
    public Class<?> getFeaturedClass() {
        return Inventory.class;
    }

    @Override
    public void addProductType(ProductType productType) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addProductType(ProductType productType, String catalogIdentifier) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeProductType(ProductIdentifier id) {
        // TODO Auto-generated method stub

    }

    @Override
    public ProductType findProductTypeByCatalogIdentifier(String catalogIdentifier) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProductType findProductTypeByProductIdentifier(ProductIdentifier id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<ProductType> findProductTypeByCategory(String category) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<ProductType> findProductTypeByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

}