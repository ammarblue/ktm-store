package org.ktm.web.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.ktm.actions.KTMAction;
import org.ktm.dao.KTMEMDaoFactory;
import org.ktm.dao.party.AuthenDao;
import org.ktm.dao.party.PersonDao;
import org.ktm.domain.party.Authen;
import org.ktm.domain.party.Person;
import org.ktm.exception.CreateException;
import org.ktm.exception.DeleteException;
import org.ktm.web.form.FrmAuthen;
import org.ktm.web.form.FrmDomain;

public class AuthenManager extends FrmManagerAbstractImpl {

    private static AuthenManager theInstance = null;

    public static AuthenManager getInstance() {
        if (theInstance == null) {
            theInstance = new AuthenManager();
        }
        return theInstance;
    }

    private AuthenDao getDao(KTMAction action) {
        return KTMEMDaoFactory.getInstance().getAuthenDao(action);
    }

    @Override
    public synchronized FrmDomain get(KTMAction action, Serializable tryId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public synchronized List<FrmDomain> findAll(KTMAction action) {
        PersonDao personDao = KTMEMDaoFactory.getInstance().getPersonDao(action);
        AuthenDao authenDao = KTMEMDaoFactory.getInstance().getAuthenDao(action);
        Collection<Person> persons = (Collection<Person>) personDao.findAll();
        List<FrmDomain> authens = new ArrayList<FrmDomain>();

        for (Person person : persons) {
            FrmAuthen form = new FrmAuthen();
            syncFormPerson(person, form);
            Authen authen = (Authen) authenDao.findByPartyId(person.getUniqueId());
            syncFormAuthen(authen, form);
            authens.add((FrmDomain) form);
        }
        return authens;
    }

    @Override
    public Integer delete(KTMAction action, Serializable tryId) {
        try {
            return getDao(action).delete(tryId);
        } catch (DeleteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public synchronized Integer delete(KTMAction action, FrmDomain toDelete) {
        return delete(action, toDelete.getId());
    }

    @Override
    public synchronized Integer addOrUpdate(KTMAction action, FrmDomain toAdd) {
        PersonDao personDao = KTMEMDaoFactory.getInstance().getPersonDao(action);
        FrmAuthen frmAuthen = (FrmAuthen) toAdd;
        Authen authen = new Authen();

        if (toAdd.isNew()) {

            authen.setUsername(frmAuthen.getUsername());
            authen.setPassword(frmAuthen.getPassword());
        } else {
            authen = (Authen) getDao(action).findByPartyId(Integer.parseInt(frmAuthen.getId()));
            if (authen == null) {
                authen = new Authen();
            }
            if (!frmAuthen.getPassword().equals("****")) {
                authen.setPassword(frmAuthen.getPassword());
            }
        }

        Person person = (Person) personDao.get(Integer.parseInt(frmAuthen.getId()));
        authen.setParty(person);

        Integer id = 0;
        try {
            id = (Integer) getDao(action).create(authen);
        } catch (CreateException e) {
            e.printStackTrace();
        }
        return id;
    }

    protected synchronized void syncFormAuthen(Authen authen, FrmAuthen form) {
        if (authen != null && form != null) {
            if (authen.getUsername() != null) {
                form.setUsername(authen.getUsername());
            }
            form.setPassword("****");
            form.setConfirm("");
        }
    }

    protected synchronized void syncFormPerson(Person person, FrmAuthen form) {
        if (person != null) {
            if (person.getUniqueId() != null) {
                form.setId(person.getUniqueId().toString());
            }
            if (person.getPreName() != null) {
                form.setPreName(person.getPreName());
            }
            if (person.getFirstName() != null) {
                form.setFirstName(person.getFirstName());
            }
            if (person.getLastName() != null) {
                form.setLastName(person.getLastName());
            }
        }
    }
}
