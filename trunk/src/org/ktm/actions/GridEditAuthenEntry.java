package org.ktm.actions;

import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.ktm.dao.Dao;
import org.ktm.dao.KTMEMDaoFactory;
import org.ktm.dao.party.*;
import org.ktm.domain.party.*;
import org.ktm.web.KTMAction;

@ParentPackage(value = "ktm-default")
public class GridEditAuthenEntry extends KTMAction {

    private static final long serialVersionUID = 4044617485054462060L;
    private Logger log = Logger.getLogger(GridEditAuthenEntry.class);

    private String oper = "";
    private String id;
    private String preName;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String confirm;
    private AuthenDao authenDao;
    private PersonDao personDao;

    @Actions({ @Action(value = "/grid-edit-authen-entry", results = { @Result(location = "simpleecho.jsp", name = "success"), @Result(location = "simpleecho.jsp", name = "input") }) })
    public String execute() throws Exception {
        log.debug("id :" + id);
        log.debug("preName :" + preName);
        log.debug("firstName :" + firstName);
        log.debug("lastName :" + lastName);
        log.debug("username :" + username);
        log.debug("password:" + password);
        log.debug("confirm:" + confirm);

        Authen authen = null;

        if (oper.equalsIgnoreCase("del")) {
            StringTokenizer ids = new StringTokenizer(id, ",");
            while (ids.hasMoreTokens()) {
                int removeId = Integer.parseInt(ids.nextToken());
                log.debug("Delete Authen " + removeId);
                getDao().delete(removeId);
            }
        } else {
            if (oper.equalsIgnoreCase("add")) {
                log.debug("Add Authen");
                authen = new Authen();

                authen.setUsername(username);
                authen.setPassword(password);
            } else if (oper.equalsIgnoreCase("edit")) {
                log.debug("Edit Authen");
                authen = (Authen) getDao().get(Integer.parseInt(id));
                if (authen == null) {
                    authen = new Authen();
                }
                if (!password.equals("****")) {
                    authen.setPassword(password);
                }
            }

            Person person = (Person) personDao.get(Integer.parseInt(id));
            authen.setParty(person);

            getDao().create(authen);
        }
        return SUCCESS;
    }

    @Override
    protected Dao getDao() {
        if (authenDao == null) {
            authenDao = KTMEMDaoFactory.getInstance().getAuthenDao(this);
            personDao = KTMEMDaoFactory.getInstance().getPersonDao(this);
        }
        return authenDao;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPreName() {
        return preName;
    }

    public void setPreName(String preName) {
        this.preName = preName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

}
