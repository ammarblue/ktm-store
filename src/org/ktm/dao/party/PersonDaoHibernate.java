package org.ktm.dao.party;

import java.util.List;
import org.ktm.actions.KTMAction;
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
