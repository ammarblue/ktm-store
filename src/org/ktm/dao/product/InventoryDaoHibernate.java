package org.ktm.dao.product;

import java.util.List;
import java.util.Set;
import org.ktm.dao.AbstractDao;
import org.ktm.domain.product.*;

public class InventoryDaoHibernate extends AbstractDao implements InventoryDao {

    private static final long serialVersionUID = 8685358823453581488L;

    @Override
    public Class<?> getFeaturedClass() {
        return Inventory.class;
    }

    @Override
    public List<?> getSubList(List<?> cols, int form, int to) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<?> findNotById(List<?> cols, int id, int from, int to) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<?> findGreaterAsId(List<?> list, int id, int from, int to) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<?> findLesserAsId(List<?> list, int id, int from, int to) {
        // TODO Auto-generated method stub
        return null;
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