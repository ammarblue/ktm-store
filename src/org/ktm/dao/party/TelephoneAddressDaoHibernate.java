package org.ktm.dao.party;

import org.ktm.dao.AbstractDao;
import org.ktm.dao.PersistanceType;
import org.ktm.domain.party.TelephoneAddress;
import org.ktm.web.KTMAction;

public abstract class TelephoneAddressDaoHibernate extends AbstractDao implements TelephoneAddressDao {

	private static final long serialVersionUID = 7584799141117100983L;

	public TelephoneAddressDaoHibernate(KTMAction curdAction) {
		super(curdAction, PersistanceType.HIBERNATE);
	}

	@Override
	public Class<?> getFeaturedClass() {
		return TelephoneAddress.class;
	}

}
