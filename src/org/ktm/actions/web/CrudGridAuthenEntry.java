package org.ktm.actions.web;

import java.security.NoSuchAlgorithmException;
import java.util.StringTokenizer;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.ktm.actions.KTMAction;
import org.ktm.encode.KTMCrypt;
import org.ktm.web.form.FrmAuthen;
import org.ktm.web.manager.FormManager;
import org.ktm.web.manager.ServiceLocator;

@ParentPackage(value = "ktm-default")
public class CrudGridAuthenEntry extends KTMAction {

    private static final long serialVersionUID = 4044617485054462060L;
    private Logger            log              = Logger.getLogger(CrudGridAuthenEntry.class);

    private String            oper             = "";
    private String            id;
    private String            prename;
    private String            firstname;
    private String            lastname;
    private String            username;
    private String            password;

    @Actions({ @Action(value = "/crud-grid-authen", results = { @Result(location = "simpleecho.jsp", name = "success"), @Result(location = "simpleecho.jsp", name = "input") }) })
    public String execute() throws Exception {
        log.debug("id :" + id);
        log.debug("prename :" + prename);
        log.debug("firstname :" + firstname);
        log.debug("lastname :" + lastname);
        log.debug("username :" + username);

        try {
            password = KTMCrypt.SHA1(username + password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (oper.equalsIgnoreCase("del")) {
            StringTokenizer ids = new StringTokenizer(id, ",");
            while (ids.hasMoreTokens()) {
                int removeId = Integer.parseInt(ids.nextToken());
                log.debug("Delete Authen " + removeId);
                getManager().delete(removeId);
            }
        } else {
            FrmAuthen frmAuthen = new FrmAuthen();
            frmAuthen.setPrename(prename);
            frmAuthen.setFirstname(firstname);
            frmAuthen.setLastname(lastname);
            frmAuthen.setUsername(username);
            frmAuthen.setPassword(password);

            if (oper.equalsIgnoreCase("add")) {
                log.debug("Add Authen");
                frmAuthen.setNew(true);
            } else if (oper.equalsIgnoreCase("edit")) {
                log.debug("Edit Authen");
                frmAuthen.setId(Integer.parseInt(id));
                frmAuthen.setNew(false);
            }

            getManager().addOrUpdate(frmAuthen);
        }
        return SUCCESS;
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

    public String getPrename() {
        return prename;
    }

    public void setPrename(String prename) {
        this.prename = prename;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    @Override
    protected FormManager getManager() {
        return ServiceLocator.getAuthenManager();
    }

}
