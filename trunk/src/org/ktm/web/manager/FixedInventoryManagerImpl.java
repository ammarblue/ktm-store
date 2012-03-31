package org.ktm.web.manager;

import org.ktm.dao.KTMEMDaoFactory;
import org.ktm.dao.product.InventoryDao;

public class FixedInventoryManagerImpl extends InventoryManagerImpl implements InventoryManager {

    private static FixedInventoryManagerImpl theInstance = null;

    public static FixedInventoryManagerImpl getInstance() {
        if (theInstance == null) {
            theInstance = new FixedInventoryManagerImpl();
        }
        return theInstance;
    }
    
    protected InventoryDao getDao() {
        return KTMEMDaoFactory.getInstance().getFixedInventoryDao();
    }
}
