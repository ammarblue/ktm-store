package org.ktm.stock.dao;

import org.ktm.dao.Dao;
import org.ktm.exception.UpdateException;

public interface KTMMaxIdentifierDao extends Dao {
    
    public String getMaxPurchaseOrderId() throws UpdateException;
    public String getMaxSalesOrderId() throws UpdateException;

}
