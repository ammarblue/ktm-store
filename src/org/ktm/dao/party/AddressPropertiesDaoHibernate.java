package org.ktm.dao.party;

import java.util.List;
import org.ktm.actions.KTMAction;
import org.ktm.dao.AbstractDao;
import org.ktm.dao.PersistanceType;
import org.ktm.domain.party.*;

public class AddressPropertiesDaoHibernate  extends AbstractDao implements AddressPropertiesDao {

	private static final long serialVersionUID = -4112741897016255345L;

	public AddressPropertiesDaoHibernate(KTMAction curdAction) {
		super(curdAction, PersistanceType.HIBERNATE);
	}

	@Override
	public Class<?> getFeaturedClass() {
		return AddressProperties.class;
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
