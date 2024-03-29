package org.ktm.dao.product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.ktm.dao.AbstractHibernateStorageDao;
import org.ktm.domain.money.Price;
import org.ktm.domain.product.BeveragePackage;
import org.ktm.domain.product.PackageType;

public class PackageTypeDaoHibernate extends AbstractHibernateStorageDao implements PackageTypeDao {

    private static final long serialVersionUID = 1L;

    @Override
    public List<PackageType> getComponents() {
        return null;
    }

    @Override
    public List<Price> getPrices() {
        return null;
    }

    @Override
    public void addProductSet(Set<PackageType> products) {

    }

    @Override
    public Class<?> getFeaturedClass() {
        return PackageType.class;
    }

    @Override
    public void addProductType(PackageType product) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<PackageType> findByCatalog(Integer id) {
        List<PackageType> result = null;

        try {
            String queryString = "select new list(pck) " + "FROM PackageType AS pck " + "WHERE pck.catalogEntry.productCatalog.uniqueId = :catalogId";

            Query query = getCurrentSession().createQuery(queryString);
            query.setInteger("catalogId", id);

            query.setFirstResult(getFirstResult());

            if (getMaxResults() < QUERY_MAX_RESULTS_DEFAULT) {
                query.setMaxResults(getMaxResults());
            }

            List<PackageType> objs = new ArrayList<PackageType>();
            for (Iterator<?> objectIt = query.list().iterator(); objectIt.hasNext();) {
                Object object = objectIt.next();

                if (object instanceof PackageType || object instanceof BeveragePackage) {
                    objs.add((PackageType) object);
                } else if (object instanceof Collection) {
                    Collection<?> subList = (Collection<?>) object;
                    for (Object listObject : subList) {
                        if (listObject instanceof PackageType || object instanceof BeveragePackage) {
                            objs.add((PackageType) listObject);
                        }
                    }
                    break;
                }
            }
            result = objs;
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return result;
    }
}
