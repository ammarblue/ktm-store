package org.ktm.dao.order;

import java.util.List;
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

}
