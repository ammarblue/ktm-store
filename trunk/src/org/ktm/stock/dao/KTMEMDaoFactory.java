package org.ktm.stock.dao;

import org.ktm.core.KTMContext;
import org.ktm.dao.EDatabaseSystem;
import org.ktm.dao.party.AuthenDao;
import org.ktm.dao.party.CustomerDao;
import org.ktm.dao.party.EmploymentDao;
import org.ktm.dao.party.OrganizationDao;
import org.ktm.dao.party.PartyRoleDao;
import org.ktm.dao.party.PartyRoleTypeDao;
import org.ktm.dao.party.PersonDao;
import org.ktm.dao.party.SupplierDao;
import org.ktm.dao.product.BeveragePackageDao;
import org.ktm.dao.product.CatalogEntryDao;
import org.ktm.dao.product.InventoryDao;
import org.ktm.dao.product.PackageTypeDao;
import org.ktm.dao.product.ProductCatalogDao;
import org.ktm.dao.product.ProductTypeDao;

public abstract class KTMEMDaoFactory {

    public static final KTMEMDaoFactory HIBERNATE = new KTMEMDaoFactoryHibernate();
    public static final KTMEMDaoFactory MANGODB   = new KTMEMDaoFactoryMongoDB();

    public static final KTMEMDaoFactory DEFAULT   = HIBERNATE;

    public static KTMEMDaoFactory getInstance() {
        if (KTMContext.databaseSystem == EDatabaseSystem.MONGODB) { return MANGODB; }
        return DEFAULT;
    }

    public abstract AuthenDao getAuthenDao();

    public abstract PersonDao getPersonDao();

    public abstract PartyRoleDao getPartyRoleDao();

    public abstract PackageTypeDao getPackageTypeDao();

    public abstract ProductCatalogDao getProductCatalogDao();

    public abstract BeveragePackageDao getBeveragePackageDao();

    public abstract SupplierDao getSupplierDao();

    public abstract OrganizationDao getOrganizationDao();

    public abstract InventoryDao getInventoryDao();

    public abstract InventoryDao getFixedInventoryDao();

    public abstract InventoryDao getMovingInventoryDao();

    public abstract CustomerDao getCustomerDao();

    public abstract EmploymentDao getEmploymentDao();

    public abstract KTMMaxIdentifierDao getKTMMaxIdentifierDao();

    public abstract org.ktm.dao.party.PartyDao getPartyDao();

    public abstract CatalogEntryDao getCatalogEntryDao();

    public abstract ProductTypeDao getProductTypeDao();

    public abstract PartyRoleTypeDao getPartyRoleTypeDao();
}
