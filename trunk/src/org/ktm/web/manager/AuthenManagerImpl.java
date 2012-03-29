package org.ktm.web.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.ktm.actions.KTMAction;
import org.ktm.core.KTMContext;
import org.ktm.dao.KTMEMDaoFactory;
import org.ktm.dao.party.AuthenDao;
import org.ktm.dao.party.PersonDao;
import org.ktm.domain.party.Authen;
import org.ktm.domain.party.Person;
import org.ktm.exception.CreateException;
import org.ktm.exception.DeleteException;
import org.ktm.web.form.FrmAuthen;
import org.ktm.web.form.FrmDomain;
import com.opensymphony.xwork2.ActionContext;

public class AuthenManagerImpl extends FrmManagerAbstractImpl implements AuthenManager {

    private static AuthenManagerImpl theInstance = null;

    public static AuthenManagerImpl getInstance() {
        if (theInstance == null) {
            theInstance = new AuthenManagerImpl();
        }
        return theInstance;
    }

    private AuthenDao getDao() {
        return KTMEMDaoFactory.getInstance().getAuthenDao();
    }

    @Override
    public synchronized FrmDomain get(Serializable tryId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public synchronized List<FrmDomain> findAll() {
        PersonDao personDao = KTMEMDaoFactory.getInstance().getPersonDao();
        AuthenDao authenDao = KTMEMDaoFactory.getInstance().getAuthenDao();
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
    public Integer delete(Serializable tryId) {
        try {
            return getDao().delete(tryId);
        } catch (DeleteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public synchronized Integer delete(FrmDomain toDelete) {
        return delete(toDelete.getId());
    }

    @Override
    public synchronized Integer addOrUpdate(FrmDomain toAdd) {
        PersonDao personDao = KTMEMDaoFactory.getInstance().getPersonDao();
        FrmAuthen frmAuthen = (FrmAuthen) toAdd;
        Authen authen = new Authen();

        if (toAdd.isNew()) {
            authen.setUsername(frmAuthen.getUsername());
            authen.setPassword(frmAuthen.getPassword());
        } else {
            authen = (Authen) getDao().findByPartyId(frmAuthen.getId());
            if (authen == null) {
                authen = new Authen();
            }
            if (!frmAuthen.getPassword().equals("****")) {
                authen.setPassword(frmAuthen.getPassword());
            }
            authen.setUsername(frmAuthen.getUsername());
        }

        Person person = (Person) personDao.get(frmAuthen.getId());
        authen.setParty(person);

        Integer id = 0;
        try {
            id = (Integer) getDao().create(authen);
        } catch (CreateException e) {
            e.printStackTrace();
        }
        return id;
    }

    public synchronized void syncFormAuthen(Authen authen, FrmAuthen form) {
        if (authen != null && form != null) {
            if (authen.getUsername() != null) {
                form.setUsername(authen.getUsername());
            }
            form.setPassword("****");
            form.setConfirm("");
        }
    }

    public synchronized void syncFormPerson(Person person, FrmAuthen form) {
        if (person != null) {
            if (person.getUniqueId() != null) {
                form.setId(person.getUniqueId());
            }
            if (person.getPrename() != null) {
                KTMContext context = (KTMContext) ActionContext.getContext().get(KTMAction.CURRENT_CONTEXT);
                String txt = context.getAction().getText("page.common.prename_" + person.getPrename());
                form.setPrename(txt);
            }
            if (person.getFirstname() != null) {
                form.setFirstname(person.getFirstname());
            }
            if (person.getLastname() != null) {
                form.setLastname(person.getLastname());
            }
        }
    }
}
