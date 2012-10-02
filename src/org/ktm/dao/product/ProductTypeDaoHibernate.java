package org.ktm.dao.product;

import org.ktm.dao.AbstractHibernateStorageDao;

public class ProductTypeDaoHibernate extends AbstractHibernateStorageDao implements ProductTypeDao {

    private static final long serialVersionUID = 1L;

    @Override
    public Class<?> getFeaturedClass() {
        return ProductTypeDaoHibernate.class;
    }

}
