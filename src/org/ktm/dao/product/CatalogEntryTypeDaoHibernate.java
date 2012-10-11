package org.ktm.dao.product;

import java.util.List;
import org.ktm.dao.AbstractHibernateStorageDao;
import org.ktm.domain.product.CatalogEntryType;

public class CatalogEntryTypeDaoHibernate extends AbstractHibernateStorageDao implements CatalogEntryTypeDao {

    private static final long serialVersionUID = 1L;

    @Override
    public Class<?> getFeaturedClass() {
        return CatalogEntryType.class;
    }

    @SuppressWarnings("unchecked")
    public List<CatalogEntryType> findAll() {
        return (List<CatalogEntryType>) super.findAll();
    }
}
