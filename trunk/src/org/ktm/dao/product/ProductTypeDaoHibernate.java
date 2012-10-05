package org.ktm.dao.product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.LazyInitializationException;
import org.ktm.dao.AbstractHibernateStorageDao;
import org.ktm.dao.KTMEMDaoFactory;
import org.ktm.domain.product.CatalogEntry;
import org.ktm.domain.product.ProductType;

public class ProductTypeDaoHibernate extends AbstractHibernateStorageDao implements ProductTypeDao {

    private static final long serialVersionUID = 1L;

    @Override
    public Class<?> getFeaturedClass() {
        return ProductType.class;
    }

    @Override
    public List<ProductType> findByCatalogEntry(Integer selectedCatalogEntryId) {
        List<ProductType> result = new ArrayList<ProductType>();
        try {
            CatalogEntryDao cEntryDao = KTMEMDaoFactory.getInstance().getCatalogEntryDao();
            CatalogEntry cEntry = (CatalogEntry) cEntryDao.get(selectedCatalogEntryId);
            if (cEntry != null) {
                Iterator<ProductType> it = cEntry.getProductType().iterator();
                while (it.hasNext()) {
                    ProductType ptype = it.next();
                    if (ptype instanceof ProductType) {
                        result.add(ptype);
                    }
                }
            }
        } catch (LazyInitializationException lze) {

        }
        return result;
    }

}
