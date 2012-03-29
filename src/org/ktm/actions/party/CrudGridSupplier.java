package org.ktm.actions.party;

import java.util.StringTokenizer;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.ktm.actions.CrudAction;
import org.ktm.web.form.FrmSupplier;
import org.ktm.web.manager.ServiceLocator;
import org.ktm.web.manager.SupplierManager;

public class CrudGridSupplier extends CrudAction {

    private static final long serialVersionUID = 3928207560159104965L;
    private Logger            log              = Logger.getLogger(CrudGridSupplier.class);

    private String            oper             = "";
    private String            id;
    private String            identifier;
    private String            desc;
    private String            addr1;
    private String            addr2;
    private String            addr3;
    private String            tel;
    private String            fax;
    private String            payMethod;
    private String            payDuration;
    private String            contactName;
    private String            mark;
    
    @Override
    protected SupplierManager getManager() {
        return ServiceLocator.getSupplierManager();
    }

    @Actions({ @Action(value = "/crud-grid-supplier", results = { @Result(location = "simpleecho.jsp", name = "success"), @Result(location = "simpleecho.jsp", name = "input") }) })
    public String execute() throws Exception {
        log.debug("id: " + id);
        log.debug("identifier: " + identifier);
        log.debug("desc: " + desc);
        log.debug("addr1: " + addr1);
        log.debug("addr2: " + addr2);
        log.debug("addr3: " + addr3);
        log.debug("tel: " + tel);
        log.debug("fax: " + fax);
        log.debug("payMethod: " + payMethod);
        log.debug("payDuration: " + payDuration);
        log.debug("contactName: " + contactName);
        log.debug("mark: " + mark);

        initContext();

        if (oper.equalsIgnoreCase("del")) {
            StringTokenizer ids = new StringTokenizer(id, ",");
            while (ids.hasMoreTokens()) {
                int removeId = Integer.parseInt(ids.nextToken());
                log.debug("Delete productType " + removeId);
                getManager().delete(removeId);
            }
        } else {
            FrmSupplier form = new FrmSupplier();
            form.setIdentifier(identifier);
            form.setDesc(desc);
            form.setAddr1(addr1);
            form.setAddr2(addr2);
            form.setAddr3(addr3);
            form.setTel(tel);
            form.setFax(fax);
            form.setPayMethod(payMethod);
            form.setPayDuration(payDuration);
            form.setContactName(contactName);
            form.setMark(mark);

            if (oper.equalsIgnoreCase("add")) {
                log.debug("Add new Supplier");
                form.setNew(true);
            } else if (oper.equalsIgnoreCase("edit")) {
                log.debug("Edit Supplier");
                form.setId(Integer.parseInt(id));
                form.setNew(false);
            }
            
            getManager().addOrUpdate(form);
        }
        return SUCCESS;
    }

    public String getOper() {
        return oper;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public String getAddr3() {
        return addr3;
    }

    public void setAddr3(String addr3) {
        this.addr3 = addr3;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getPayDuration() {
        return payDuration;
    }

    public void setPayDuration(String payDuration) {
        this.payDuration = payDuration;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
    
}