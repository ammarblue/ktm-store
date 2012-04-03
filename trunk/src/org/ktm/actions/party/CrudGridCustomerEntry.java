package org.ktm.actions.party;

import java.util.StringTokenizer;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.ktm.actions.CrudAction;
import org.ktm.web.form.FrmCustomer;
import org.ktm.web.manager.FormManager;
import org.ktm.web.manager.ServiceLocator;

public class CrudGridCustomerEntry extends CrudAction {

    private static final long serialVersionUID = -3454448309088641394L;
    private Logger            log              = Logger.getLogger(CrudGridCustomerEntry.class);

    private String            oper             = "";
    private String            id;
    private String            identifier;
    private String            registeredIdentifier;
    private String            prename;
    private String            firstname;
    private String            lastname;
    private String            birthDay;
    private String            emailAddress;
    private String            tel;
    private String            desc;
    
    @Override
    protected FormManager getManager() {
        return ServiceLocator.getCustomerManager();
    }
    
    @Actions({ @Action(value = "/crud-grid-customer", results = { @Result(location = "simpleecho.jsp", name = "success") }) })
    public String execute() throws Exception {
        log.debug("id :" + id);
        log.debug("identifier :" + identifier);
        log.debug("registeredIdentifier :" + registeredIdentifier);
        log.debug("prename :" + prename);
        log.debug("firstname :" + firstname);
        log.debug("lastname :" + lastname);
        log.debug("birthDay :" + birthDay);
        log.debug("emailAddress:" + emailAddress);
        log.debug("tel:" + tel);
        log.debug("desc: " + desc);

        initContext();
        
        if (oper.equalsIgnoreCase("del")) {
            StringTokenizer ids = new StringTokenizer(id, ",");
            while (ids.hasMoreTokens()) {
                int removeId = Integer.parseInt(ids.nextToken());
                log.debug("Delete Person " + removeId);
                getManager().delete(removeId);
            }
        } else {
            FrmCustomer frmCustomer = new FrmCustomer();
            frmCustomer.setIdentifier(identifier);
            frmCustomer.setRegisteredIdentifier(registeredIdentifier);
            frmCustomer.setPrename(prename);
            frmCustomer.setFirstname(firstname);
            frmCustomer.setLastname(lastname);
            frmCustomer.setBirthDay(birthDay);
            frmCustomer.setEmailAddress(emailAddress);
            frmCustomer.setTel(tel);
            frmCustomer.setDesc(desc);

            if (oper.equalsIgnoreCase("add")) {
                log.debug("Add Person");
                frmCustomer.setNew(true);
            } else if (oper.equalsIgnoreCase("edit")) {
                log.debug("Edit Person");
                frmCustomer.setId(Integer.parseInt(id));
                frmCustomer.setNew(false);
            }

            getManager().addOrUpdate(frmCustomer);
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

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getRegisteredIdentifier() {
        return registeredIdentifier;
    }

    public void setRegisteredIdentifier(String registeredIdentifier) {
        this.registeredIdentifier = registeredIdentifier;
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

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
