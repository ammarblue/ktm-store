package org.ktm.stock.database;

import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ktm.crypt.KTMCrypt;
import org.ktm.dao.KTMEMDaoFactory;
import org.ktm.dao.party.PersonDao;
import org.ktm.domain.party.Authen;
import org.ktm.domain.party.Employee;
import org.ktm.domain.party.PartyRoleIdentifier;
import org.ktm.domain.party.Person;
import org.ktm.exception.CreateException;
import org.ktm.exception.DeleteException;
import org.ktm.servlet.ActionForward;
import org.ktm.servlet.CRUDServlet;
import org.ktm.stock.bean.FormBean;
import org.ktm.stock.bean.PersonBean;

@WebServlet("/CRUDPerson")
public class CRUDPersonServlet extends CRUDServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public String getBeanClass() {
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

    public ActionForward savePerson(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, CreateException {
        PersonBean bean = (PersonBean) form;
        PersonDao personDao = KTMEMDaoFactory.getInstance().getPersonDao();
        Person person = new Person();
        boolean needAuthenOnNewPerson = false;

        if (!bean.getUniqueId().isEmpty()) {
            Integer id = Integer.valueOf(bean.getUniqueId());
            person = (Person) personDao.get(id);
            bean.syncToPerson(person);
        } else {
            bean.syncToPerson(person);
            needAuthenOnNewPerson = true;
        }

        if (person.getRoles().size() <= 0) {
            Employee emp = new Employee();
            PartyRoleIdentifier identifier = new PartyRoleIdentifier();
            identifier.setIdentifier("emp0001");
            emp.setIdentifier(identifier);
            emp.setParty(person);
            person.getRoles().add(emp);
        }

        Serializable personId = personDao.createOrUpdate(person);

        if (needAuthenOnNewPerson && personId != null) {
            person = (Person) personDao.get(personId);
            if (person != null) {
                Authen authen = new Authen();
                authen.setParty(person);

                try {
                    bean.setPassword(KTMCrypt.SHA1(bean.getUsername() + bean.getPassword()));
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

                authen.setUsername(bean.getUsername());
                authen.setPassword(bean.getPassword());

                KTMEMDaoFactory.getInstance().getAuthenDao().createOrUpdate(authen);
            }
        }

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
    /*
     * public ActionForward storePerson(FormBean form, HttpServletRequest
     * request, HttpServletResponse response) throws ServletException,
     * IOException, DeleteException { store(request, response);
     * closeSession(request); return ActionForward.getAction(this, request,
     * "CRUDPerson?method=list", true); }
     */
}
