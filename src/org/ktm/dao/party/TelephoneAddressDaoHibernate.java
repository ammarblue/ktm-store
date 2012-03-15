package org.ktm.dao.party;

import org.ktm.dao.AbstractDao;
import org.ktm.domain.party.TelephoneAddress;

public abstract class TelephoneAddressDaoHibernate extends AbstractDao implements TelephoneAddressDao {

    private static final long serialVersionUID = 7584799141117100983L;

    @Override
    public Class<?> getFeaturedClass() {
        return TelephoneAddress.class;
    }

}
