package org.ktm.dao.party;

import org.ktm.domain.party.Organization;

public class OrganizationDaoHibernate extends PartyDaoHibernate implements OrganizationDao {

    private static final long serialVersionUID = 1L;

    @Override
    public Class<?> getFeaturedClass() {
        return Organization.class;
    }

}
