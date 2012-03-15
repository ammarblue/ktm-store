package org.ktm.dao;

import org.ktm.dao.party.AuthenDao;
import org.ktm.dao.party.PartyRoleDao;
import org.ktm.dao.party.PersonDao;
import org.ktm.dao.product.PackageTypeDao;
import org.ktm.dao.product.InventoryDao;
import org.ktm.dao.product.ProductCatalogDao;

public abstract class KTMEMDaoFactory {

    public static final KTMEMDaoFactory HIBERNATE = new KTMEMDaoFactoryHibernate();

    public static final KTMEMDaoFactory DEFAULT   = HIBERNATE;

    public static KTMEMDaoFactory getInstance() {
        return DEFAULT;
    }

    public abstract AuthenDao getAuthenDao();

    public abstract PersonDao getPersonDao();

    public abstract PartyRoleDao getPartyRoleDao();

    public abstract InventoryDao getInventoryDao();

    public abstract PackageTypeDao getPackageTypeDao();

    public abstract ProductCatalogDao getProductCatalogDao();
}
