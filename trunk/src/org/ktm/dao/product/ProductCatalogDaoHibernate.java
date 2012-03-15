package org.ktm.dao.product;

import java.util.List;
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

}
