package org.ktm.dao.product;

import java.util.List;
import java.util.Set;
import org.ktm.dao.Dao;
import org.ktm.domain.money.*;
import org.ktm.domain.product.*;

public interface PackageTypeDao extends Dao {

    void addProductType(ProductType product);
    
    List<ProductType> getComponents();
    
    List<Price> getPrices();
    
    void addProductSet(Set<ProductType> products);
}
