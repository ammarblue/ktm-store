package org.ktm.web.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.ktm.dao.KTMEMDaoFactory;
import org.ktm.dao.product.ProductCatalogDao;
import org.ktm.domain.product.ProductCatalog;
import org.ktm.domain.product.ProductIdentifier;
import org.ktm.domain.product.ProductType;
import org.ktm.exception.CreateException;
import org.ktm.web.form.FrmCatalog;
import org.ktm.web.form.FrmDomain;

public class ProductCatalogManagerImpl extends FrmManagerAbstractImpl implements ProductCatalogManager {

    private static ProductCatalogManagerImpl theInstance = null;
    
    public static ProductCatalogManagerImpl getInstance() {
        if (theInstance == null) {
            theInstance = new ProductCatalogManagerImpl();
        }
        return theInstance;
    }

    @Override
    public FrmDomain get(Serializable tryId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<FrmDomain> findAll() {
        List<FrmDomain> result = new ArrayList<FrmDomain>();
        ProductCatalogDao dao = KTMEMDaoFactory.getInstance().getProductCatalogDao();
        
        List<ProductCatalog> catalogs = (List<ProductCatalog>) dao.findAll();
        for (ProductCatalog catalog : catalogs) {
            FrmCatalog frmCatalog = new FrmCatalog();
            frmCatalog.setId(catalog.getUniqueId());
            frmCatalog.setIdentifier(catalog.getIdentifier());
            frmCatalog.setName(catalog.getName());
            result.add(frmCatalog);
        }
        return result;
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
        Integer result = -1;
        if (toAdd != null && toAdd instanceof FrmCatalog) {
            ProductCatalogDao dao = KTMEMDaoFactory.getInstance().getProductCatalogDao();
            FrmCatalog form = (FrmCatalog) toAdd;
            ProductCatalog catalog = null;
            
            if (toAdd.isNew()) {
                catalog = new ProductCatalog();
            } else {
                catalog = (ProductCatalog) dao.get(form.getId());
            }
            
            catalog.setIdentifier(form.getIdentifier());
            catalog.setName(form.getName());
            
            try {
                result = (Integer) dao.create(catalog);
            } catch (CreateException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public void addProductType(ProductType productType) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addProductType(ProductType productType, String catalogIdentifier) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeProductType(ProductIdentifier id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<?> findProductType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<?> findProductTypeByCatalogIdentifier(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProductType findProductTypeByProductIdentifier(ProductIdentifier id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<?> findProductTypeByCategory(String category) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<?> findProductTypeByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

}
