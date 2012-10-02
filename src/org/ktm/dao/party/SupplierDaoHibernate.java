package org.ktm.dao.party;

import org.ktm.dao.AbstractHibernateStorageDao;
import org.ktm.domain.party.Supplier;

public class SupplierDaoHibernate extends AbstractHibernateStorageDao implements SupplierDao {

    private static final long serialVersionUID = 1L;

    @Override
    public Class<?> getFeaturedClass() {
        return Supplier.class;
    }

}
