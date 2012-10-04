package org.ktm.dao.product;

import java.util.List;
import org.ktm.dao.Dao;
import org.ktm.domain.product.CatalogEntry;

public interface CatalogEntryDao extends Dao {

    public List<CatalogEntry> findAll();

}
