package org.ktm.dao;

import org.ktm.actions.KTMAction;
import org.ktm.dao.party.AuthenDao;
import org.ktm.dao.party.AuthenDaoHibernate;
import org.ktm.dao.party.PartyRoleDao;
import org.ktm.dao.party.PartyRoleDaoHibernate;
import org.ktm.dao.party.PersonDao;
import org.ktm.dao.party.PersonDaoHibernate;
import org.ktm.dao.product.PackageTypeDao;
import org.ktm.dao.product.PackageTypeDaoHibernate;
import org.ktm.dao.product.InventoryDao;
import org.ktm.dao.product.InventoryDaoHibernate;
import org.ktm.dao.product.ProductCatalogDao;
import org.ktm.dao.product.ProductCatalogDaoHibernate;

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
}
