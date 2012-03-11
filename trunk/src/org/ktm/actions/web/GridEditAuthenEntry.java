package org.ktm.actions.web;

import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.ktm.actions.KTMAction;
import org.ktm.web.form.FrmAuthen;
import org.ktm.web.manager.FormManager;
import org.ktm.web.manager.ServiceLocator;

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

    @Actions({ @Action(value = "/grid-edit-authen-entry", results = { @Result(location = "simpleecho.jsp", name = "success"), @Result(location = "simpleecho.jsp", name = "input") }) })
    public String execute() throws Exception {
        FrmAuthen frmAuthen = new FrmAuthen();
        frmAuthen.setId(id);
        frmAuthen.setPreName(preName);
        frmAuthen.setFirstName(firstName);
        frmAuthen.setLastName(lastName);
        frmAuthen.setUsername(username);
        frmAuthen.setPassword(password);
        
        
        log.debug("id :" + frmAuthen.getId());
        log.debug("preName :" + frmAuthen.getPreName());
        log.debug("firstName :" + frmAuthen.getFirstName());
        log.debug("lastName :" + frmAuthen.getLastName());
        log.debug("username :" + frmAuthen.getUsername());
        log.debug("password:" + frmAuthen.getPassword());
        log.debug("confirm:" + frmAuthen.getConfirm());

        if (oper.equalsIgnoreCase("del")) {
            StringTokenizer ids = new StringTokenizer(frmAuthen.getId(), ",");
            while (ids.hasMoreTokens()) {
                int removeId = Integer.parseInt(ids.nextToken());
                log.debug("Delete Authen " + removeId);
                getManager().delete(this, removeId);
            }
        } else {
            if (oper.equalsIgnoreCase("add")) {
                log.debug("Add Authen");
                frmAuthen.setNew(true);
            } else if (oper.equalsIgnoreCase("edit")) {
                log.debug("Edit Authen");
                frmAuthen.setNew(false);
            }
            
            getManager().addOrUpdate(this, frmAuthen);
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

    @Override
    protected FormManager getManager() {
        return ServiceLocator.getAuthenManager();
    }

}
