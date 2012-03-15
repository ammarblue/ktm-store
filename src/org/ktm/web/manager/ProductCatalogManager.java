package org.ktm.web.manager;

import java.util.List;
import org.ktm.domain.product.ProductIdentifier;
import org.ktm.domain.product.ProductType;

public interface ProductCatalogManager extends FormManager {

    public void addProductType(ProductType productType);
    
    public void addProductType(ProductType productType, String catalogIdentifier);
    
    public void removeProductType(ProductIdentifier id);
    
    public List<?> findProductType();
    
    public List<?> findProductTypeByCatalogIdentifier(String id);
    
    public ProductType findProductTypeByProductIdentifier(ProductIdentifier id);
    
    public List<?> findProductTypeByCategory(String category);
    
    public List<?> findProductTypeByName(String name);
    
}
