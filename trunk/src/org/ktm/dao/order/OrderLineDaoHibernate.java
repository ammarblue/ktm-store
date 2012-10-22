package org.ktm.dao.order;

import java.util.Collection;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.ktm.dao.AbstractHibernateStorageDao;
import org.ktm.domain.order.OrderLine;
import org.ktm.domain.order.OrderLineIdentifier;

public class OrderLineDaoHibernate extends AbstractHibernateStorageDao implements OrderLineDao {

    private static final long serialVersionUID = 1L;

    @Override
    public Class<?> getFeaturedClass() {
        return OrderLine.class;
    }

    @Override
    public OrderLine findByIdentifier(OrderLineIdentifier identifier) {
        if (identifier != null) { return findByIdentifier(identifier.getIdentifier()); }
        return null;
    }

    @Override
    public OrderLine findByIdentifier(String identifier) {
        OrderLine result = null;
        String queryString = "select orderLine FROM OrderLine AS orderLine WHERE orderLine.identifier = :identifier";

        try {
            Query query = getCurrentSession().createQuery(queryString);
            query.setParameter("identifier", identifier);

            query.setFirstResult(getFirstResult());
            if (getMaxResults() < QUERY_MAX_RESULTS_DEFAULT) {
                query.setMaxResults(getMaxResults());
            }

            for (Iterator<?> objectIt = query.list().iterator(); objectIt.hasNext();) {
                Object object = objectIt.next();

                if (object instanceof OrderLine) {
                    result = (OrderLine) object;
                    break;
                } else if (object instanceof Collection) {
                    Collection<?> subList = (Collection<?>) object;
                    for (Object listObject : subList) {
                        if (listObject instanceof OrderLine) {
                            result = (OrderLine) listObject;
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

}
