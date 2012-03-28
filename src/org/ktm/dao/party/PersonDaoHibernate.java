package org.ktm.dao.party;

import org.ktm.domain.party.Person;

public class PersonDaoHibernate extends PartyDaoHibernate implements PersonDao {

    private static final long serialVersionUID = 3013635539244787741L;

    public Class<?> getFeaturedClass() {
        return Person.class;
    }
}
