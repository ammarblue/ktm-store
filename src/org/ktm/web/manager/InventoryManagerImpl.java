package org.ktm.web.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.ktm.dao.KTMEMDaoFactory;
import org.ktm.dao.product.InventoryDao;
import org.ktm.domain.product.FixedInventory;
import org.ktm.domain.product.Inventory;
import org.ktm.domain.product.MovingInventory;
import org.ktm.exception.CreateException;
import org.ktm.exception.DeleteException;
import org.ktm.web.form.FrmDomain;
import org.ktm.web.form.FrmInventory;
import org.ktm.web.form.FrmInventoryEntry;
import org.ktm.web.form.FrmProductInstance;
import org.ktm.web.form.FrmProductType;

public class InventoryManagerImpl extends FrmManagerAbstractImpl implements InventoryManager {

    private static InventoryManagerImpl theInstance = null;

    public static InventoryManagerImpl getInstance() {
        if (theInstance == null) {
            theInstance = new InventoryManagerImpl();
        }
        return theInstance;
    }
    
    protected InventoryDao getDao() {
        return KTMEMDaoFactory.getInstance().getInventoryDao();
    }
    
    private void syncToForm(Inventory ivn, FrmInventory form) {
        if (ivn.getUniqueId() != null) {
            form.setId(ivn.getUniqueId());
        }
        if (ivn.getIdentifier() != null) {
            form.setIdentifier(ivn.getIdentifier());
        }
        if (ivn.getName() != null) {
            form.setName(ivn.getName());
        }
        
        if (ivn instanceof MovingInventory) {
            MovingInventory vi = (MovingInventory) ivn;
            form.setModelName(vi.getModelName());
            form.setOwnerName(vi.getOwnerName());
            form.setVehicleRegistration(vi.getVehicleRegistration());
            form.setInventoryType("MovingInventory");
        } else if (ivn instanceof FixedInventory){
            form.setInventoryType("FixedInventory");
        }
    }
    
    @Override
    public FrmDomain get(Serializable tryId) {
        FrmInventory form = new FrmInventory();
        InventoryDao dao = getDao();
        Inventory sup = (Inventory) dao.get(tryId);
        if (sup != null) {
            syncToForm(sup, form);
        }
        return form;
    }

    @Override
    public List<FrmDomain> findAll() {
        List<FrmDomain> result = new ArrayList<FrmDomain>();
        InventoryDao dao = getDao();
        if (dao != null) {
            Collection<?> objs = dao.findAll();
            for (Object obj : objs) {
                if (obj instanceof Inventory) {
                    FrmInventory form = new FrmInventory();
                    syncToForm((Inventory)obj, form);
                    result.add(form);
                }
            }
        }
        return result;
    }

    @Override
    public Integer delete(Serializable tryId) {
        try {
            InventoryDao dao = getDao();
            return dao.delete(tryId);
        } catch (DeleteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer delete(FrmDomain toDelete) {
        return delete(toDelete.getId());
    }

    @Override
    public Integer addOrUpdate(FrmDomain toAdd) {
        Integer id = null;
        Inventory ivn = null;
        if (toAdd != null) {
            FrmInventory form = (FrmInventory) toAdd;
            InventoryDao dao = getDao();
            
            if (form.isNew()) {
                if (form.getInventoryType().equals("FixedInventory")) {
                    ivn = new FixedInventory();
                } else if (form.getInventoryType().equals("MovingInventory")) {
                    ivn = new MovingInventory();
                }
            } else {
                ivn = (Inventory) dao.get(form.getId());
            }
            
            ivn.setIdentifier(form.getIdentifier());
            ivn.setName(form.getName());
            
            if (ivn instanceof MovingInventory) {
                MovingInventory vi = (MovingInventory) ivn;
                vi.setModelName(form.getModelName());
                vi.setOwnerName(form.getOwnerName());
                vi.setVehicleRegistration(form.getVehicleRegistration());
            }
            
            try {
                id = (Integer) dao.create(ivn);
            } catch (CreateException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    @Override
    public void addInventoryEntry(FrmInventoryEntry inventoryEntry) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeInventoryEntry(FrmInventoryEntry inventoryEntry) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<FrmInventoryEntry> findInventoryEntry(String productIdentifier) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<FrmInventoryEntry> getInventoryEntries() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<FrmProductType> getProductTypes() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addProductInstance(FrmInventoryEntry inventoryEntry, FrmProductInstance product) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeProductInstance(FrmInventoryEntry inventoryEntry, FrmProductInstance product, Integer amount) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public FrmProductInstance findProductInstance(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer getNumberOfProductInstancesAvailable(FrmProductType productType) {
        // TODO Auto-generated method stub
        return null;
    }
}
