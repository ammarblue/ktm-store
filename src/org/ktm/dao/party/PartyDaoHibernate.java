package org.ktm.dao.party;

import org.ktm.dao.AbstractHibernateStorageDao;
import org.ktm.domain.party.Party;

public class PartyDaoHibernate extends AbstractHibernateStorageDao implements PartyDao {

    private static final long serialVersionUID = 1L;

    @Override
    public Class<?> getFeaturedClass() {
        return Party.class;
    }

}
