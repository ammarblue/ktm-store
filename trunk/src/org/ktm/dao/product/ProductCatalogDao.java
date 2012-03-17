package org.ktm.dao.product;

import org.ktm.dao.Dao;
import org.ktm.domain.product.ProductCatalog;

public interface ProductCatalogDao extends Dao {
    
    public ProductCatalog findByName(String name);

}
