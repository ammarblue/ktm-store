package org.ktm.dao.order;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.ktm.dao.AbstractHibernateStorageDao;
import org.ktm.domain.order.PurchaseOrder;

public class PurchaseOrderDaoHibernate extends AbstractHibernateStorageDao implements PurchaseOrderDao {

    private static final long serialVersionUID = 1L;

    @Override
    public Class<?> getFeaturedClass() {
        return PurchaseOrder.class;
    }

    @SuppressWarnings("unchecked")
    public List<PurchaseOrder> findAll() {
        return (List<PurchaseOrder>) super.findAll();
    }

    @Override
    public PurchaseOrder findByOrderId(String orderId) {
        PurchaseOrder result = null;
        String queryString = "select purchaseOrder FROM PurchaseOrder AS purchaseOrder WHERE purchaseOrder.identifier.identifier = :orderId";

        try {
            Query query = getCurrentSession().createQuery(queryString);
            query.setParameter("orderId", orderId);

            query.setFirstResult(getFirstResult());
            if (getMaxResults() < QUERY_MAX_RESULTS_DEFAULT) {
                query.setMaxResults(getMaxResults());
            }

            for (Iterator<?> objectIt = query.list().iterator(); objectIt.hasNext();) {
                Object object = objectIt.next();

                if (object instanceof PurchaseOrder) {
                    result = (PurchaseOrder) object;
                    break;
                } else if (object instanceof Collection) {
                    Collection<?> subList = (Collection<?>) object;
                    for (Object listObject : subList) {
                        if (listObject instanceof PurchaseOrder) {
                            result = (PurchaseOrder) listObject;
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
