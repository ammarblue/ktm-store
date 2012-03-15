package org.ktm.dao.product;

import java.util.List;
import java.util.Set;
import org.ktm.dao.Dao;
import org.ktm.domain.money.Price;
import org.ktm.domain.product.PackageType;

public interface PackageTypeDao extends Dao {

    void addProductType(PackageType product);
    
    List<PackageType> getComponents();
    
    List<Price> getPrices();
    
    void addProductSet(Set<PackageType> products);

    List<PackageType> findByCatalog(Integer id);
}
