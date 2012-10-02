package org.ktm.dao.party;

import org.ktm.dao.AbstractHibernateStorageDao;
import org.ktm.domain.party.Employment;

public class EmploymentDaoHibernate extends AbstractHibernateStorageDao implements EmploymentDao {

    private static final long serialVersionUID = 1L;

    @Override
    public Class<?> getFeaturedClass() {
        return Employment.class;
    }

}
