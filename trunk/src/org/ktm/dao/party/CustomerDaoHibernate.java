package org.ktm.dao.party;

import org.ktm.dao.AbstractHibernateStorageDao;
import org.ktm.domain.party.Customer;

public class CustomerDaoHibernate extends AbstractHibernateStorageDao implements CustomerDao {

    private static final long serialVersionUID = 1L;

    @Override
    public Class<?> getFeaturedClass() {
        return Customer.class;
    }

}
