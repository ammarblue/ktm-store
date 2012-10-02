package org.ktm.dao.party;

import java.util.Collection;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.ktm.core.KTMContext;
import org.ktm.dao.AbstractHibernateStorageDao;
import org.ktm.domain.party.Authen;
import org.ktm.utils.HibernateUtil;

public class AuthenDaoHibernate extends AbstractHibernateStorageDao implements AuthenDao {

    private static final long serialVersionUID = 1L;

    @Override
    public Class<?> getFeaturedClass() {
        return Authen.class;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public Authen findByUsername(String username) {
        Authen result = null;
        String queryString = "select authen FROM Authen AS authen WHERE authen.username = :username";

        Session session = HibernateUtil.getSession(KTMContext.getSession());
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Query query = session.createQuery(queryString);
            query.setParameter("username", username);

            query.setFirstResult(getFirstResult());
            if (getMaxResults() < QUERY_MAX_RESULTS_DEFAULT) {
                query.setMaxResults(getMaxResults());
            }

            for (Iterator objectIt = query.list().iterator(); objectIt.hasNext();) {
                Object object = objectIt.next();

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
            transaction.commit();
        } catch (HibernateException he) {
            transaction.rollback();
            he.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public Authen findByPartyId(Integer id) {
        Authen result = null;

        Session session = HibernateUtil.getSession(KTMContext.getSession());
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            String queryString = "select new list(authen) " + "FROM Authen AS authen " + "WHERE authen.party.uniqueId = :partyId";

            Query query = session.createQuery(queryString);
            query.setParameter("partyId", id);

            query.setFirstResult(getFirstResult());

            if (getMaxResults() < QUERY_MAX_RESULTS_DEFAULT) {
                query.setMaxResults(getMaxResults());
            }

            for (Iterator objectIt = query.list().iterator(); objectIt.hasNext();) {
                Object object = objectIt.next();

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
            transaction.commit();
        } catch (HibernateException he) {
            transaction.rollback();
            he.printStackTrace();
        }
        return result;
    }

}
