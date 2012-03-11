package org.ktm.dao;

import org.ktm.actions.KTMAction;
import org.ktm.dao.party.*;

public class KTMEMDaoFactoryHibernate extends KTMEMDaoFactory {

	@Override
	public AuthenDao getAuthenDao(KTMAction action) {
		return new AuthenDaoHibernate(action);
	}

	@Override
	public PersonDao getPersonDao(KTMAction action) {
		return new PersonDaoHibernate(action);
	}

    @Override
    public PartyRoleDao getPartyRoleDao(KTMAction action) {
        return new PartyRoleDaoHibernate(action);
    }
}
