package org.ktm.dao.order;

import java.util.List;
import org.ktm.dao.Dao;
import org.ktm.domain.order.PurchaseOrder;

public interface PurchaseOrderDao extends Dao {

    public List<PurchaseOrder> findAll();

    public PurchaseOrder findByOrderId(String orderId);

}
