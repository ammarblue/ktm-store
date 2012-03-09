package org.ktm.dao.party;

import org.ktm.dao.AbstractDao;
import org.ktm.dao.PersistanceType;
import org.ktm.domain.party.*;
import org.ktm.web.KTMAction;

public class AddressPropertiesDaoHibernate  extends AbstractDao implements AddressPropertiesDao {

	private static final long serialVersionUID = -4112741897016255345L;

	public AddressPropertiesDaoHibernate(KTMAction curdAction) {
		super(curdAction, PersistanceType.HIBERNATE);
	}

	@Override
	public Class<?> getFeaturedClass() {
		return AddressProperties.class;
	}

}
