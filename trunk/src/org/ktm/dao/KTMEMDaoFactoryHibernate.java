package org.ktm.dao;

import org.ktm.dao.party.AuthenDao;
import org.ktm.dao.party.AuthenDaoHibernate;
import org.ktm.dao.party.CustomerDao;
import org.ktm.dao.party.CustomerDaoHibernate;
import org.ktm.dao.party.EmploymentDao;
import org.ktm.dao.party.EmploymentDaoHibernate;
import org.ktm.dao.party.OrganizationDao;
import org.ktm.dao.party.OrganizationDaoHibernate;
import org.ktm.dao.party.PartyDao;
import org.ktm.dao.party.PartyDaoHibernate;
import org.ktm.dao.party.PartyRoleDao;
import org.ktm.dao.party.PartyRoleDaoHibernate;
import org.ktm.dao.party.PersonDao;
import org.ktm.dao.party.PersonDaoHibernate;
import org.ktm.dao.party.SupplierDao;
import org.ktm.dao.party.SupplierDaoHibernate;
import org.ktm.dao.product.BeveragePackageDao;
import org.ktm.dao.product.BeveragePackageDaoHibernate;
import org.ktm.dao.product.CatalogEntryDao;
import org.ktm.dao.product.CatalogEntryDaoHibernate;
import org.ktm.dao.product.FixedInventoryDaoHibernate;
import org.ktm.dao.product.InventoryDao;
import org.ktm.dao.product.InventoryDaoHibernate;
import org.ktm.dao.product.MovingInventoryDaoHibernate;
import org.ktm.dao.product.PackageTypeDao;
import org.ktm.dao.product.PackageTypeDaoHibernate;
import org.ktm.dao.product.ProductCatalogDao;
import org.ktm.dao.product.ProductCatalogDaoHibernate;
import org.ktm.dao.product.ProductTypeDao;
import org.ktm.dao.product.ProductTypeDaoHibernate;

public class KTMEMDaoFactoryHibernate extends KTMEMDaoFactory {

    @Override
    public AuthenDao getAuthenDao() {
        return new AuthenDaoHibernate();
    }

    @Override
    public PersonDao getPersonDao() {
        return new PersonDaoHibernate();
    }

    @Override
    public PartyRoleDao getPartyRoleDao() {
        return new PartyRoleDaoHibernate();
    }

    @Override
    public PackageTypeDao getPackageTypeDao() {
        return new PackageTypeDaoHibernate();
    }

    @Override
    public ProductCatalogDao getProductCatalogDao() {
        return new ProductCatalogDaoHibernate();
    }

    @Override
    public BeveragePackageDao getBeveragePackageDao() {
        return new BeveragePackageDaoHibernate();
    }

    @Override
    public SupplierDao getSupplierDao() {
        return new SupplierDaoHibernate();
    }

    @Override
    public OrganizationDao getOrganizationDao() {
        return new OrganizationDaoHibernate();
    }

    @Override
    public InventoryDao getInventoryDao() {
        return new InventoryDaoHibernate();
    }

    @Override
    public InventoryDao getFixedInventoryDao() {
        return new FixedInventoryDaoHibernate();
    }

    @Override
    public InventoryDao getMovingInventoryDao() {
        return new MovingInventoryDaoHibernate();
    }

    @Override
    public CustomerDao getCustomerDao() {
        return new CustomerDaoHibernate();
    }

    @Override
    public EmploymentDao getEmploymentDao() {
        return new EmploymentDaoHibernate();
    }

    @Override
    public KTMMaxIdentifierDao getKTMMaxIdentifierDao() {
        return new KTMMaxIdentifierDaoHibernate();
    }

    @Override
    public ProductTypeDao getProductTypeDao() {
        return new ProductTypeDaoHibernate();
    }

    @Override
    public CatalogEntryDao getCatalogEntryDao() {
        return new CatalogEntryDaoHibernate();
    }

    @Override
    public PartyDao getPartyDao() {
        return new PartyDaoHibernate();
    }
}
