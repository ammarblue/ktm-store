package org.ktm.dao.party;

import org.ktm.dao.AbstractDao;
import org.ktm.dao.PersistanceType;
import org.ktm.domain.party.RegisteredIdentifier;
import org.ktm.web.KTMAction;

public class RegisteredIdentifierDaoHibernate extends AbstractDao implements RegisteredIdentifierDao {

	private static final long serialVersionUID = 5204963426613650477L;

	public RegisteredIdentifierDaoHibernate(KTMAction curdAction) {
		super(curdAction, PersistanceType.HIBERNATE);
	}
	
	@Override
	public Class<?> getFeaturedClass() {
		return RegisteredIdentifier.class;
	}

}
