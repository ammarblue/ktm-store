package org.ktm.stock.database;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.ktm.crypt.KTMCrypt;
import org.ktm.dao.KTMEMDaoFactory;
import org.ktm.dao.party.PersonDao;
import org.ktm.domain.party.AddressProperties;
import org.ktm.domain.party.Authen;
import org.ktm.domain.party.EmailAddress;
import org.ktm.domain.party.Employee;
import org.ktm.domain.party.Party;
import org.ktm.domain.party.PartyIdentifier;
import org.ktm.domain.party.PartyRole;
import org.ktm.domain.party.PartyRoleIdentifier;
import org.ktm.domain.party.Person;
import org.ktm.domain.party.RegisteredIdentifier;
import org.ktm.exception.CreateException;
import org.ktm.exception.DeleteException;
import org.ktm.servlet.ActionForward;
import org.ktm.servlet.DispatchServlet;
import org.ktm.stock.bean.FormBean;
import org.ktm.stock.bean.PersonBean;
import org.ktm.utils.HibernateUtil;

@WebServlet("/CRUDPerson")
public class CRUDPersonServlet extends DispatchServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected String getBeanClass() {
        return "org.ktm.stock.bean.PersonBean";
    }

    public ActionForward listPerson(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersonDao personDao = KTMEMDaoFactory.getInstance().getPersonDao();
        List<Person> persons = personDao.findAll();
        PersonBean bean = (PersonBean) form;
        bean.loadFormCollection(persons);
        return ActionForward.getUri(this, request, "database/ListAllPerson.jsp");
    }

    public ActionForward newPerson(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return ActionForward.getUri(this, request, "database/EditPerson.jsp");
    }

    public ActionForward savePerson1(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CreateException {
        // getModel(request, createNew(request));
        getModel(request, 1);
        return ActionForward.getAction(this, request, "CRUDPerson?method=list", true);
    }

    public ActionForward savePerson(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CreateException {
        PersonBean bean = (PersonBean) form;
        PersonDao personDao = KTMEMDaoFactory.getInstance().getPersonDao();
        Person person = new Person();

        if (!bean.getUniqueId().isEmpty()) {
            Integer id = Integer.valueOf(bean.getUniqueId());
            person = (Person) personDao.get(id);
            bean.syncToPerson(person);
            bean.setIsNewObject(false);
        } else {
            bean.syncToPerson(person);
            bean.setIsNewObject(true);
        }

        if (!bean.isNewObject()) {
            Authen authen = null;
            Set<Authen> authens = person.getAuthens();
            Iterator<Authen> ait = authens.iterator();
            while (ait.hasNext()) {
                Authen aobj = ait.next();
                if (aobj.getParty().getUniqueId() == person.getUniqueId()) {
                    authen = aobj;
                    break;
                }
            }
            if (authen == null) {
                authen = new Authen();
                authen.setParty(person);
                person.getAuthens().add(authen);
            }

            try {
                bean.setPassword(KTMCrypt.SHA1(bean.getUsername() + bean.getPassword()));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            authen.setUsername(bean.getUsername());
            authen.setPassword(bean.getPassword());
        }

        if (person.getRoles().size() <= 0) {
            Employee emp = new Employee();
            PartyRoleIdentifier identifier = new PartyRoleIdentifier();
            identifier.setIdentifier("emp0001");
            emp.setIdentifier(identifier);
            emp.setParty(person);
            person.getRoles().add(emp);
        }

        personDao.create(person);

        return ActionForward.getAction(this, request, "CRUDPerson?method=list", true);
    }

    public ActionForward editPerson(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PersonBean bean = (PersonBean) form;

        PersonDao personDao = KTMEMDaoFactory.getInstance().getPersonDao();

        int id = Integer.valueOf(bean.getUniqueId());
        Person person = (Person) personDao.get(id);
        if (person != null) {
            bean.loadToForm(person);
        }

        return ActionForward.getUri(this, request, "database/EditPerson.jsp");
    }

    public ActionForward delPerson(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DeleteException {
        PersonBean bean = (PersonBean) form;

        PersonDao personDao = KTMEMDaoFactory.getInstance().getPersonDao();

        int id = Integer.valueOf(bean.getUniqueId());
        personDao.delete(id);

        return ActionForward.getAction(this, request, "CRUDPerson?method=list", true);
    }

    protected void getModel(HttpServletRequest request, int id) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSession(request.getSession());
            transaction = session.beginTransaction();
            Party party = (Party) session.get(Party.class, id);
            Set<Authen> aus = party.getAuthens();
            Iterator<Authen> it = aus.iterator();
            while (it.hasNext()) {
                Authen au = it.next();
                System.out.print(au.getUsername());
                System.out.print(au.getPassword());
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    protected int createNew(HttpServletRequest request) {
        Party party = new Party();
        PartyIdentifier identifier = new PartyIdentifier();
        identifier.setIdentifier("000000001");
        party.setIdentifier(identifier);
        RegisteredIdentifier reg = new RegisteredIdentifier();
        reg.setIdentifier("reg0000000");
        reg.setParty(party);
        party.getRegisteredIdentifiers().add(reg);
        Authen auth = new Authen();
        auth.setUsername("keng");
        auth.setPassword("keng");
        auth.setParty(party);
        party.getAuthens().add(auth);
        PartyRoleIdentifier partyRoleIdentifier = new PartyRoleIdentifier();
        partyRoleIdentifier.setIdentifier("000000000");
        PartyRole role = new PartyRole();
        role.setIdentifier(partyRoleIdentifier);
        role.setName("root");
        role.setParty(party);
        party.getRoles().add(role);

        EmailAddress email = new EmailAddress();
        email.setEmail("sarachaii@gmail.com");
        AddressProperties addProp = new AddressProperties();
        addProp.setAddress(email);
        addProp.setParty(party);
        party.getAddresses().add(addProp);

        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSession(request.getSession());
            transaction = session.beginTransaction();
            session.saveOrUpdate(party);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }

        return party.getUniqueId();
    }

}
