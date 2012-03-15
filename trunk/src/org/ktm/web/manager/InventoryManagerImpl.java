package org.ktm.web.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.ktm.dao.KTMEMDaoFactory;
import org.ktm.dao.product.InventoryDao;
import org.ktm.domain.product.Inventory;
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

    private InventoryDao getDao() {
        return KTMEMDaoFactory.getInstance().getInventoryDao();
    }
    
    @Override
    public FrmDomain get(Serializable tryId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<FrmDomain> findAll() {
        List<FrmDomain> myInventorys = new ArrayList<FrmDomain>();

        List<Inventory> inventorys = (List<Inventory>) getDao().findAll();
        for (Inventory ivn : inventorys) {
            FrmInventory fi = new FrmInventory();
            fi.setId(ivn.getUniqueId());
            fi.setIdentifier(ivn.getIdentifier());
            fi.setName(ivn.getName());
            myInventorys.add(fi);
        }
        return myInventorys;
    }

    @Override
    public Integer delete(Serializable tryId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer delete(FrmDomain toDelete) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer addOrUpdate(FrmDomain toAdd) {
        // TODO Auto-generated method stub
        return null;
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
    
    /*



    @Override
    @SuppressWarnings("unchecked")
    public synchronized List<FrmDomain> findAll(KTMAction action) {
        List<FrmDomain> myCatalogs = new ArrayList<FrmDomain>();

        List<Inventory> catalogs = (List<Inventory>) getDao(action).findAll();
        for (Inventory cate : catalogs) {
            FrmProduct p = new FrmProduct();
            p.setId(cate.getUniqueId());
            p.setIdentifier(cate.getIdentifier());
            p.setName(cate.getName());
            myCatalogs.add(p);
        }
        return myCatalogs;
    }

    @Override
    public synchronized Integer delete(KTMAction action, FrmDomain toDelete) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public synchronized FrmDomain get(KTMAction action, Serializable tryId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public synchronized Integer addOrUpdate(KTMAction action, FrmDomain toAdd) {
        Integer result = null;
        if (toAdd != null) {
            InventoryDao catalogDao = KTMEMDaoFactoryHibernate.getInstance().getProductCatalogDao(action);
            FrmProduct productCatalog = (FrmProduct) toAdd;

            Inventory catalog = null;
            if (toAdd.isNew()) {
                catalog = new Inventory();
            } else {
                catalog = (Inventory) catalogDao.get(productCatalog.getId());
            }

            catalog.setIdentifier(productCatalog.getIdentifier());
            catalog.setName(productCatalog.getName());

            try {
                result = (Integer) catalogDao.create(catalog);
            } catch (CreateException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Integer delete(KTMAction action, Serializable tryId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<FrmPackageType> findByCatalog(KTMAction action, Integer catalogIdentifier) {
        InventoryDao catalogDao = KTMEMDaoFactoryHibernate.getInstance().getProductCatalogDao(action);
        catalogDao.findProductTypeByCatalogIdentifier(catalogIdentifier);
        return null;
    }

     */

}
