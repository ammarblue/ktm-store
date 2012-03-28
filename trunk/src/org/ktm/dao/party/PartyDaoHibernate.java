package org.ktm.dao.party;

import java.util.List;
import org.ktm.dao.AbstractDao;
import org.ktm.dao.Dao;
import org.ktm.domain.party.Party;

public class PartyDaoHibernate extends AbstractDao implements Dao {

    private static final long serialVersionUID = 1L;

    @Override
    public Class<?> getFeaturedClass() {
        return Party.class;
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
