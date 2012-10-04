package org.ktm.dao.product;

import java.util.Collection;
import java.util.Iterator;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.ktm.dao.AbstractHibernateStorageDao;
import org.ktm.domain.product.ProductCatalog;

public class ProductCatalogDaoHibernate extends AbstractHibernateStorageDao implements ProductCatalogDao {

    private static final long serialVersionUID = 1L;

    @Override
    public Class<?> getFeaturedClass() {
        return ProductCatalog.class;
    }

    @Override
    public ProductCatalog findByName(String name) {
        ProductCatalog result = null;

        String queryString = "select catalog FROM ProductCatalog AS catalog WHERE catalog.name = :name";
        try {
            Query query = getCurrentSession().createQuery(queryString);
            query.setString("name", name);

            query.setFirstResult(getFirstResult());

            if (getMaxResults() < QUERY_MAX_RESULTS_DEFAULT) {
                query.setMaxResults(getMaxResults());
            }

            for (Iterator<?> objectIt = query.list().iterator(); objectIt.hasNext();) {
                Object object = objectIt.next();
                if (object instanceof ProductCatalog) {
                    result = (ProductCatalog) object;
                    break;
                } else if (object instanceof Collection) {
                    Collection<?> sublist = (Collection<?>) object;
                    for (Object objList : sublist) {
                        if (objList instanceof ProductCatalog) {
                            result = (ProductCatalog) objList;
                            break;
                        }
                    }
                }
            }
        } catch (HibernateException he) {
            he.printStackTrace();
        }

        return result;
    }

}
