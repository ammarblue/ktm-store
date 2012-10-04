package org.ktm.dao.product;

import java.util.List;
import org.ktm.dao.AbstractHibernateStorageDao;
import org.ktm.domain.product.CatalogEntry;

public class CatalogEntryDaoHibernate extends AbstractHibernateStorageDao implements CatalogEntryDao {

    private static final long serialVersionUID = 1L;

    @Override
    public Class<?> getFeaturedClass() {
        return CatalogEntry.class;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CatalogEntry> findAll() {
        return (List<CatalogEntry>) super.findAll();
    }

}
