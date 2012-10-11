package org.ktm.dao.product;

import java.util.List;
import org.ktm.dao.Dao;
import org.ktm.domain.product.CatalogEntryType;

public interface CatalogEntryTypeDao extends Dao {

    public List<CatalogEntryType> findAll();

}
