package org.ktm.dao;

import org.ktm.exception.UpdateException;

public interface KTMMaxIdentifierDao extends Dao {
    
    public String getMaxPurchaseOrderId() throws UpdateException;
    public String getMaxSalesOrderId() throws UpdateException;

}
