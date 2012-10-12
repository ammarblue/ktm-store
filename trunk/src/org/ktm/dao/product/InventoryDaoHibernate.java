package org.ktm.dao.product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.ktm.dao.AbstractHibernateStorageDao;
import org.ktm.domain.product.Inventory;
import org.ktm.domain.product.ProductIdentifier;
import org.ktm.domain.product.ProductType;

public class InventoryDaoHibernate extends AbstractHibernateStorageDao implements InventoryDao {

    private static final long serialVersionUID = 1L;

    @Override
    public Class<?> getFeaturedClass() {
        return Inventory.class;
    }

    @Override
    public void addProductType(ProductType productType) {
        // TODO Auto-generated method stub

    }

    @Override
    public void addProductType(ProductType productType, String catalogIdentifier) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeProductType(ProductIdentifier id) {
        // TODO Auto-generated method stub

    }

    @Override
    public ProductType findProductTypeByCatalogIdentifier(String catalogIdentifier) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProductType findProductTypeByProductIdentifier(ProductIdentifier id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<ProductType> findProductTypeByCategory(String category) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<ProductType> findProductTypeByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Inventory> findByInventoryType(EInventoryType type) {
        List<Inventory> result = new ArrayList<Inventory>();
        String queryString = "";

        switch (type) {
            case CENTER:
                queryString = "from CenterInventory";
                break;
            case MOVING:
                queryString = "from VehicleInventory";
                break;
            case SALE_POINT:
                queryString = "from SalePointInventory";
                break;
            default:
                queryString = "from Inventory";
                break;
        }
        try {
            Query query = getCurrentSession().createQuery(queryString);

            query.setFirstResult(getFirstResult());
            if (getMaxResults() < QUERY_MAX_RESULTS_DEFAULT) {
                query.setMaxResults(getMaxResults());
            }

            for (Iterator<?> objectIt = query.list().iterator(); objectIt.hasNext();) {
                Object object = objectIt.next();

                if (object instanceof Inventory) {
                    result.add((Inventory) object);
                } else if (object instanceof Collection) {
                    Collection<?> subList = (Collection<?>) object;
                    for (Object listObject : subList) {
                        if (listObject instanceof Inventory) {
                            result.add((Inventory) listObject);
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
