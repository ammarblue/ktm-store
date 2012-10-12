package org.ktm.stock.dao;

import org.ktm.dao.party.AuthenDao;
import org.ktm.dao.party.AuthenDaoMongoDB;
import org.ktm.dao.party.CustomerDao;
import org.ktm.dao.party.EmploymentDao;
import org.ktm.dao.party.OrganizationDao;
import org.ktm.dao.party.PartyDao;
import org.ktm.dao.party.PartyRoleDao;
import org.ktm.dao.party.PartyRoleTypeDao;
import org.ktm.dao.party.PersonDao;
import org.ktm.dao.party.SupplierDao;
import org.ktm.dao.product.BeveragePackageDao;
import org.ktm.dao.product.CatalogEntryDao;
import org.ktm.dao.product.CatalogEntryTypeDao;
import org.ktm.dao.product.InventoryDao;
import org.ktm.dao.product.PackageTypeDao;
import org.ktm.dao.product.ProductCatalogDao;
import org.ktm.dao.product.ProductTypeDao;

public class KTMEMDaoFactoryMongoDB extends KTMEMDaoFactory {

    @Override
    public AuthenDao getAuthenDao() {
        return new AuthenDaoMongoDB();
    }

    @Override
    public PersonDao getPersonDao() {
        return null;
    }

    @Override
    public PartyRoleDao getPartyRoleDao() {
        return null;
    }

    @Override
    public PackageTypeDao getPackageTypeDao() {
        return null;
    }

    @Override
    public ProductCatalogDao getProductCatalogDao() {
        return null;
    }

    @Override
    public BeveragePackageDao getBeveragePackageDao() {
        return null;
    }

    @Override
    public SupplierDao getSupplierDao() {
        return null;
    }

    @Override
    public OrganizationDao getOrganizationDao() {
        return null;
    }

    @Override
    public InventoryDao getInventoryDao() {
        return null;
    }

    @Override
    public InventoryDao getFixedInventoryDao() {
        return null;
    }

    @Override
    public InventoryDao getMovingInventoryDao() {
        return null;
    }

    @Override
    public CustomerDao getCustomerDao() {
        return null;
    }

    @Override
    public EmploymentDao getEmploymentDao() {
        return null;
    }

    @Override
    public KTMMaxIdentifierDao getKTMMaxIdentifierDao() {
        return null;
    }

    @Override
    public ProductTypeDao getProductTypeDao() {
        return null;
    }

    @Override
    public CatalogEntryDao getCatalogEntryDao() {
        return null;
    }

    @Override
    public PartyDao getPartyDao() {
        return null;
    }

    @Override
    public PartyRoleTypeDao getPartyRoleTypeDao() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CatalogEntryTypeDao getCatalogEntryTypeDao() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public InventoryDao getCenterInventoryDao() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public InventoryDao getSalePointInventoryDao() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public InventoryDao getVechileInventoryDao() {
        // TODO Auto-generated method stub
        return null;
    }
}
