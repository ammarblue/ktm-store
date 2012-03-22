package org.ktm.web.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.ktm.dao.KTMEMDaoFactory;
import org.ktm.dao.product.BeveragePackageDao;
import org.ktm.dao.product.ProductCatalogDao;
import org.ktm.domain.product.BeveragePackage;
import org.ktm.domain.product.CatalogEntry;
import org.ktm.domain.product.ProductCatalog;
import org.ktm.domain.product.ProductIdentifier;
import org.ktm.exception.CreateException;
import org.ktm.exception.DeleteException;
import org.ktm.web.form.FrmBeveragePackage;
import org.ktm.web.form.FrmDomain;

public class BeveragePackageManagerImpl extends FrmManagerAbstractImpl implements BeveragePackageManager {

    public static BeveragePackageManagerImpl theInstance = null;
    
    public static BeveragePackageManager getInstance() {
        if (theInstance == null) {
            theInstance = new BeveragePackageManagerImpl();
        }
        return theInstance;
    }
    
    @Override
    public FrmDomain get(Serializable tryId) {
        BeveragePackageDao dao = KTMEMDaoFactory.getInstance().getBeveragePackageDao();
        BeveragePackage bp = (BeveragePackage) dao.get(tryId);
        FrmBeveragePackage form = null;
        if (bp != null) {
            form = new FrmBeveragePackage();
            form.setId(bp.getUniqueId());
            form.setCatalogName(bp.getCatalogEntry().getProductCatalog().getName());
            form.setIdentifier(bp.getIdentifier().getIdentifier());
            form.setName(bp.getName());
            form.setUnitType(bp.getUnitType());
            form.setUnitCount(bp.getUnitCount());
            // TODO: get price1 and price2
            form.setPrice1(0);
            form.setPrice2(0);
        }
        return form;
    }
    
    private void syncToForm(BeveragePackage obj, FrmBeveragePackage form) {
        BeveragePackage bp = (BeveragePackage) obj;
        form.setId(bp.getUniqueId());
        form.setDescripton(bp.getDescripton());
        ProductIdentifier iden = bp.getIdentifier();
        if (iden != null) {
            form.setIdentifier(iden.getIdentifier());
        }
        form.setName(bp.getName());
        form.setNew(false);
        form.setUnitType(bp.getUnitType());
        form.setUnitCount(bp.getUnitCount());
        form.setPrice1(0.0);
        form.setPrice2(0.0);
        
        form.setCatalogName(((BeveragePackage) obj).getCatalogEntry().getProductCatalog().getName());
    }

    @Override
    public List<FrmDomain> findAll() {
        List<FrmDomain> result = new ArrayList<FrmDomain>();
        BeveragePackageDao dao = KTMEMDaoFactory.getInstance().getBeveragePackageDao();
        if (dao != null) {
            Collection<?> objs = dao.findAll();
            for (Object obj : objs) {
                if (obj instanceof BeveragePackage) {
                    FrmBeveragePackage form = new FrmBeveragePackage();
                    syncToForm((BeveragePackage) obj, form);
                    result.add(form);
                }
            }
        }
        return result;
    }

    @Override
    public Integer delete(Serializable tryId) {
        try {
            BeveragePackageDao daoBeverage = KTMEMDaoFactory.getInstance().getBeveragePackageDao();
            return daoBeverage.delete(tryId);
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
        Integer result = null;
        if (toAdd != null && toAdd instanceof FrmBeveragePackage) {
            FrmBeveragePackage form = (FrmBeveragePackage) toAdd;
            ProductCatalogDao daoCatalog = KTMEMDaoFactory.getInstance().getProductCatalogDao();
            BeveragePackageDao daoBeverage = KTMEMDaoFactory.getInstance().getBeveragePackageDao();
            
            ProductCatalog catalog = null;
            CatalogEntry entry = null;
            BeveragePackage bp = null;
            ProductIdentifier iden = null;
            
            try {
                catalog = daoCatalog.findByName(form.getCatalogName());
                List<CatalogEntry> entrys = catalog.getCatalogEntry();
                if (entrys != null && entrys.size() > 0) {
                    entry = entrys.get(0);
                } else {
                    entry = new CatalogEntry();
                    entry.setProductCatalog(catalog);
                    catalog.getCatalogEntry().add(entry);
                }
                
                if (form.isNew()) {
                    bp = new BeveragePackage();
                    iden = new ProductIdentifier();
                    bp.setIdentifier(iden);
                } else {
                    bp = (BeveragePackage) daoBeverage.get(form.getId());
                    iden = bp.getIdentifier();
                }
                entry.getProductType().add(bp);
                bp.setCatalogEntry(entry);
                
                iden.setIdentifier(form.getIdentifier());
                bp.setName(form.getName());
                form.getCatalogName();
                bp.setUnitType(form.getUnitType());
                bp.setUnitCount(form.getUnitCount());
                //bp.setPrices(prices);
                
                result = (Integer) daoCatalog.create(catalog);
            } catch (CreateException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Integer getProductCount(List<FrmBeveragePackage> myProductTypes) {
        if (myProductTypes != null) {
            return myProductTypes.size();
        }
        return null;
    }

    @Override
    public FrmBeveragePackage findById(List<FrmBeveragePackage> myProductTypes, int id) {
        if (myProductTypes != null) {
            for (FrmBeveragePackage form : myProductTypes) {
                if (form.getId() == id) {
                    return form;
                }
            }
        }
        return null;
    }

    @Override
    public List<?> findByCatalogId(Integer id) {
        List<FrmDomain> result = new ArrayList<FrmDomain>();
        if (id != null) {
            try {
                BeveragePackageDao daoBeverage = KTMEMDaoFactory.getInstance().getBeveragePackageDao();
                Collection<?> objs = daoBeverage.findByCatalog(id);

                for (Object obj : objs) {
                    if (obj instanceof BeveragePackage) {
                        FrmBeveragePackage form = new FrmBeveragePackage();
                        syncToForm((BeveragePackage) obj, form);
                        result.add(form);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
