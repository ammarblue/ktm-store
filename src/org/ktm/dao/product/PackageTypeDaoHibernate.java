package org.ktm.dao.product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.ktm.dao.AbstractDao;
import org.ktm.domain.money.Price;
import org.ktm.domain.product.PackageType;

public class PackageTypeDaoHibernate extends AbstractDao implements PackageTypeDao {

    private static final long serialVersionUID = -8979762984033084626L;

    public List<PackageType> getComponents() {
        return null;
    }

    public List<Price> getPrices() {
        return null;
    }

    public void addProductSet(Set<PackageType> products) {

    }

    @Override
    public Class<?> getFeaturedClass() {
        return PackageType.class;
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
    public void addProductType(PackageType product) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<PackageType> findByCatalog(Integer id) {
        List<PackageType> result = null;
        try {
            String queryString = "select new list(pck) " + "FROM PackageType AS pck " + "WHERE pck.catalogEntry.catalog.uniqueId = :catalogId";

            Query query = getStorage().getQuery(queryString);
            query.setParameter("partyId", id);

            query.setFirstResult(getFirstResult());

            if (getMaxResults() < QUERY_MAX_RESULTS_DEFAULT) {
                query.setMaxResults(getMaxResults());
            }

            List<PackageType> objs = new ArrayList<PackageType>();
            for (Iterator<?> objectIt = query.list().iterator(); objectIt.hasNext();) {
                Object object = (Object) objectIt.next();

                if (object instanceof PackageType) {
                    objs.add((PackageType) object);
                } else if (object instanceof Collection) {
                    Collection<?> subList = (Collection<?>) object;
                    for (Object listObject : subList) {
                        if (listObject instanceof PackageType) {
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
