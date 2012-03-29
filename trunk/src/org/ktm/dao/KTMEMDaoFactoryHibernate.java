package org.ktm.dao;

import org.ktm.dao.party.AuthenDao;
import org.ktm.dao.party.AuthenDaoHibernate;
import org.ktm.dao.party.OrganizationDao;
import org.ktm.dao.party.OrganizationDaoHibernate;
import org.ktm.dao.party.PartyRoleDao;
import org.ktm.dao.party.PartyRoleDaoHibernate;
import org.ktm.dao.party.PersonDao;
import org.ktm.dao.party.PersonDaoHibernate;
import org.ktm.dao.party.SupplierDao;
import org.ktm.dao.party.SupplierDaoHibernate;
import org.ktm.dao.product.BeveragePackageDao;
import org.ktm.dao.product.BeveragePackageDaoHibernate;
import org.ktm.dao.product.PackageTypeDao;
import org.ktm.dao.product.PackageTypeDaoHibernate;
import org.ktm.dao.product.InventoryDao;
import org.ktm.dao.product.InventoryDaoHibernate;
import org.ktm.dao.product.ProductCatalogDao;
import org.ktm.dao.product.ProductCatalogDaoHibernate;
import org.ktm.dao.product.VehicleInventoryDao;
import org.ktm.dao.product.VehicleInventoryDaoHibernate;

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
    public InventoryDao getInventoryDao() {
        return new InventoryDaoHibernate();
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
    public VehicleInventoryDao getVehicleDao() {
        return new VehicleInventoryDaoHibernate();
    }
}
