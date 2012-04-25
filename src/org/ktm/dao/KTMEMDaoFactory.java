package org.ktm.dao;

import org.ktm.dao.party.AuthenDao;
import org.ktm.dao.party.CustomerDao;
import org.ktm.dao.party.EmploymentDao;
import org.ktm.dao.party.OrganizationDao;
import org.ktm.dao.party.PartyRoleDao;
import org.ktm.dao.party.PersonDao;
import org.ktm.dao.party.SupplierDao;
import org.ktm.dao.product.BeveragePackageDao;
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
}