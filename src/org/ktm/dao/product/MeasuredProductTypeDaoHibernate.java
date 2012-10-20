package org.ktm.dao.product;

import org.ktm.domain.product.MeasuredProductType;

public class MeasuredProductTypeDaoHibernate extends ProductTypeDaoHibernate implements MeasuredProductTypeDao {

    private static final long serialVersionUID = 1L;

    @Override
    public Class<?> getFeaturedClass() {
        return MeasuredProductType.class;
    }

}
