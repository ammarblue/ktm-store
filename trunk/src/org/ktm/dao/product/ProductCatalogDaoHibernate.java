package org.ktm.dao.product;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.ktm.dao.AbstractDao;
import org.ktm.domain.product.ProductCatalog;

public class ProductCatalogDaoHibernate extends AbstractDao implements ProductCatalogDao {
    
    private static final long serialVersionUID = 7594898267883132366L;

    @Override
    public Class<?> getFeaturedClass() {
        return ProductCatalog.class;
    }

    @Override
    public List<?> getSubList(List<?> cols, int form, int to) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<?> findNotById(List<?> cols, int id, int from, int to) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<?> findGreaterAsId(List<?> list, int id, int from, int to) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<?> findLesserAsId(List<?> list, int id, int from, int to) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProductCatalog findByName(String name) {
        ProductCatalog result = null;
        String queryString = "select catalog FROM ProductCatalog AS catalog WHERE catalog.name = :name";

        Query query = getStorage().getQuery(queryString);
        query.setString("name", name);

        query.setFirstResult(getFirstResult());

        if (getMaxResults() < QUERY_MAX_RESULTS_DEFAULT) {
            query.setMaxResults(getMaxResults());
        }

        for (Iterator<?> objectIt = query.list().iterator(); objectIt.hasNext();) {
            Object object =  objectIt.next();
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
        
        return result;
    }

}
