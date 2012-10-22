package org.ktm.dao.order;

import org.ktm.dao.Dao;
import org.ktm.domain.order.OrderLine;
import org.ktm.domain.order.OrderLineIdentifier;

public interface OrderLineDao extends Dao {

    OrderLine findByIdentifier(OrderLineIdentifier identifier);

    OrderLine findByIdentifier(String identifier);

}
