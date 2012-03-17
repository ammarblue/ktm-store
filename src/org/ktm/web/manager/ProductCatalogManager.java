package org.ktm.web.manager;

import java.util.List;
import org.ktm.domain.product.ProductIdentifier;
import org.ktm.domain.product.ProductType;
import org.ktm.web.form.FrmCatalog;

public interface ProductCatalogManager extends FormManager {
    
    public FrmCatalog findByName(String name);

    public void addProductType(ProductType productType);
    
    public void addProductType(ProductType productType, String catalogIdentifier);
    
    public void removeProductType(ProductIdentifier id);
    
    public List<?> findProductType();
    
    public List<?> findProductTypeByCatalogIdentifier(String id);
    
    public ProductType findProductTypeByProductIdentifier(ProductIdentifier id);
    
    public List<?> findProductTypeByCategory(String category);
    
    public List<?> findProductTypeByName(String name);
    
}
