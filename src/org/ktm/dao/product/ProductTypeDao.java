package org.ktm.dao.product;

import java.util.List;
import org.ktm.dao.Dao;
import org.ktm.domain.product.ProductType;

public interface ProductTypeDao extends Dao {

    List<ProductType> findByCatalogEntry(Integer valueOf);

}
