package org.ktm.dao.product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.LazyInitializationException;
import org.ktm.dao.AbstractHibernateStorageDao;
import org.ktm.domain.product.CatalogEntryType;
import org.ktm.domain.product.ProductType;
import org.ktm.stock.dao.KTMEMDaoFactory;

public class ProductTypeDaoHibernate extends AbstractHibernateStorageDao implements ProductTypeDao {

    private static final long serialVersionUID = 1L;

    @Override
    public Class<?> getFeaturedClass() {
        return ProductType.class;
    }

    @Override
    public List<ProductType> findByCatalogEntryType(Integer selectedCatalogEntryTypeId) {
        List<ProductType> result = new ArrayList<ProductType>();
        try {
            CatalogEntryTypeDao catalogEntryTypeDao = KTMEMDaoFactory.getInstance().getCatalogEntryTypeDao();
            CatalogEntryType catalogEntryType = (CatalogEntryType) catalogEntryTypeDao.get(selectedCatalogEntryTypeId);
            if (catalogEntryType != null) {
                Iterator<ProductType> it = catalogEntryType.getProductType().iterator();
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
