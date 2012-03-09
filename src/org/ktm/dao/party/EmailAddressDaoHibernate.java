package org.ktm.dao.party;

import org.ktm.dao.AbstractDao;
import org.ktm.dao.PersistanceType;
import org.ktm.domain.party.EmailAddress;
import org.ktm.web.KTMAction;

public class EmailAddressDaoHibernate extends AbstractDao implements EmailAddressDao {

	private static final long serialVersionUID = 7584799141117100983L;

	public EmailAddressDaoHibernate(KTMAction curdAction) {
		super(curdAction, PersistanceType.HIBERNATE);
	}

	@Override
	public Class<?> getFeaturedClass() {
		return EmailAddress.class;
	}

}
