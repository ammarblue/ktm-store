package org.ktm.dao.party;

import java.util.List;
import org.ktm.dao.AbstractDao;
import org.ktm.domain.party.TelephoneAddress;

public class TelephoneAddressDaoHibernate extends AbstractDao implements TelephoneAddressDao {

    private static final long serialVersionUID = 7584799141117100983L;

    @Override
    public Class<?> getFeaturedClass() {
        return TelephoneAddress.class;
    }

    @Override
    public List<?> getSubList(List<?> cols, int form, int to) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<?> findNotById(List<?> cols, int id, int from, int to) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<?> findGreaterAsId(List<?> list, int id, int from, int to) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<?> findLesserAsId(List<?> list, int id, int from, int to) {
        // TODO Auto-generated method stub
        return null;
    }

}
