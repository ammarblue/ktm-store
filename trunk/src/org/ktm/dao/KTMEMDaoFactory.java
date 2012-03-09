package org.ktm.dao;

import org.ktm.dao.party.*;
import org.ktm.web.KTMAction;

public abstract class KTMEMDaoFactory {

    public static final KTMEMDaoFactory HIBERNATE = new KTMEMDaoFactoryHibernate();

    public static final KTMEMDaoFactory DEFAULT = HIBERNATE;
    
    public static KTMEMDaoFactory getInstance() {
        return DEFAULT;
    }
    
    public abstract AuthenDao getAuthenDao(KTMAction action);
    public abstract PersonDao getPersonDao(KTMAction action);
    public abstract PartyRoleDao getPartyRoleDao(KTMAction action);
}
