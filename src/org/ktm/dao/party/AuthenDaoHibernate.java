package org.ktm.dao.party;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.ktm.actions.KTMAction;
import org.ktm.dao.AbstractDao;
import org.ktm.dao.PersistanceType;
import org.ktm.domain.party.*;

public class AuthenDaoHibernate extends AbstractDao implements AuthenDao {

	private static final long serialVersionUID = -4224224421030924258L;

	public AuthenDaoHibernate(KTMAction curdAction) {
		super(curdAction, PersistanceType.HIBERNATE);
	}

	@Override
	public Class<?> getFeaturedClass() {
		return Authen.class;
	}
	
	@SuppressWarnings("rawtypes")
	public Authen findByUsername(String username) {
		Authen result = null;
		try {
			String queryString = "select authen FROM Authen AS authen WHERE authen.username = :username";
			
			Query query = getStorage().getQuery(queryString);
			query.setParameter("username", username);

			query.setFirstResult(getFirstResult());
			
			if (getMaxResults() < QUERY_MAX_RESULTS_DEFAULT) {
				query.setMaxResults(getMaxResults());
			}
			
			for (Iterator objectIt = query.list().iterator(); objectIt.hasNext(); ) {
				Object object = (Object) objectIt.next();

				if (object instanceof Authen) {
					result = (Authen) object;
				} else if (object instanceof Collection) {
					Collection subList = (Collection) object;
					for (Object listObject : subList) {
						if (listObject instanceof Authen) {
							result = (Authen) object;
							break;
						}
					}
				}
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public Authen findByPartyId(Integer id) {
		Authen result = null;
		try {
			String queryString = "select new list(authen) " +
					"FROM Authen AS authen " +
					"WHERE authen.party.uniqueId = :partyId";
	            
			Query query = getStorage().getQuery(queryString);
			query.setParameter("partyId", id);
			
			query.setFirstResult(getFirstResult());
			
			if (getMaxResults() < QUERY_MAX_RESULTS_DEFAULT) {
				query.setMaxResults(getMaxResults());
			}
			
			for (Iterator objectIt = query.list().iterator(); objectIt.hasNext(); ) {
				Object object = (Object) objectIt.next();

				if (object instanceof Authen) {
				    result = (Authen) object;
				    break;
				} else if (object instanceof Collection) {
					Collection subList = (Collection) object;
					for (Object listObject : subList) {
						if (listObject instanceof Authen) {
		                    result = (Authen) listObject;
		                    break;
						}
					}
					break;
				}
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		}
		return result;
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
