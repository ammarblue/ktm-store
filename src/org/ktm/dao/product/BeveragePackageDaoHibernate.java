package org.ktm.dao.product;

import org.ktm.domain.product.BeveragePackage;

public class BeveragePackageDaoHibernate extends PackageTypeDaoHibernate implements BeveragePackageDao {

    private static final long serialVersionUID = 612787892277585782L;

    @Override
    public Class<?> getFeaturedClass() {
        return BeveragePackage.class;
    }
}
