package org.ktm.dao.party;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.ktm.dao.AbstractDao;
import org.ktm.domain.party.*;

public class PartyRoleDaoHibernate extends AbstractDao implements PartyRoleDao {

    private static final long serialVersionUID = -4224224421030924258L;

    @Override
    public Class<?> getFeaturedClass() {
        return Authen.class;
    }

    @SuppressWarnings("rawtypes")
    public PartyRole findByRoleName(Party party, String roleName) {
        PartyRole result = null;
        try {
            String queryString = "select partyrole FROM PartyRole AS partyrole WHERE partyrole.name = :rolename AND partyrole.party.uniqueId=:partyid";

            Query query = getStorage().getQuery(queryString);
            query.setParameter("partyid", party.getUniqueId());
            query.setParameter("rolename", roleName);

            query.setFirstResult(getFirstResult());

            if (getMaxResults() < QUERY_MAX_RESULTS_DEFAULT) {
                query.setMaxResults(getMaxResults());
            }

            for (Iterator objectIt = query.list().iterator(); objectIt.hasNext();) {
                Object object = (Object) objectIt.next();

                if (object instanceof PartyRole) {
                    result = (PartyRole) object;
                } else if (object instanceof Collection) {
                    Collection subList = (Collection) object;
                    for (Object listObject : subList) {
                        if (listObject instanceof PartyRole) {
                            result = (PartyRole) object;
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
