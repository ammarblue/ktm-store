package org.ktm.dao.party;

import org.ktm.web.KTMAction;
import org.ktm.dao.AbstractDao;
import org.ktm.dao.PersistanceType;
import org.ktm.domain.party.Person;

public class PersonDaoHibernate extends AbstractDao implements PersonDao {

	public PersonDaoHibernate(KTMAction curdAction) {
		super(curdAction, PersistanceType.HIBERNATE);
	}

	private static final long serialVersionUID = 3013635539244787741L;

	public Class<?> getFeaturedClass() {
		return Person.class;
	}
}
