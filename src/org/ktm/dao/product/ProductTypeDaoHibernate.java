package org.ktm.dao.product;

import java.util.List;
import org.ktm.dao.AbstractHibernateStorageDao;
import org.ktm.domain.product.ProductType;

public class ProductTypeDaoHibernate extends AbstractHibernateStorageDao implements ProductTypeDao {

    private static final long serialVersionUID = 1L;

    @Override
    public Class<?> getFeaturedClass() {
        return ProductTypeDaoHibernate.class;
    }

    @Override
    public List<ProductType> findByCatalogEntry(Integer valueOf) {
        // TODO Auto-generated method stub
        return null;
    }

}
