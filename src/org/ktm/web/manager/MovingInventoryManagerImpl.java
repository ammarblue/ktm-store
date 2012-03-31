package org.ktm.web.manager;

import org.ktm.dao.KTMEMDaoFactory;
import org.ktm.dao.product.InventoryDao;

public class MovingInventoryManagerImpl extends InventoryManagerImpl implements InventoryManager {

    private static MovingInventoryManagerImpl theInstance = null;

    public static MovingInventoryManagerImpl getInstance() {
        if (theInstance == null) {
            theInstance = new MovingInventoryManagerImpl();
        }
        return theInstance;
    }
    
    protected InventoryDao getDao() {
        return KTMEMDaoFactory.getInstance().getMovingInventoryDao();
    }
}
