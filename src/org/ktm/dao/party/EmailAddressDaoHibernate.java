package org.ktm.dao.party;

import java.util.List;
import org.ktm.actions.KTMAction;
import org.ktm.dao.AbstractDao;
import org.ktm.dao.PersistanceType;
import org.ktm.domain.party.EmailAddress;

public class EmailAddressDaoHibernate extends AbstractDao implements EmailAddressDao {

    private static final long serialVersionUID = 7584799141117100983L;

    public EmailAddressDaoHibernate(KTMAction curdAction) {
        super(curdAction, PersistanceType.HIBERNATE);
    }

    @Override
    public Class<?> getFeaturedClass() {
        return EmailAddress.class;
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
