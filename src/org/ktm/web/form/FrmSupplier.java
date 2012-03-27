package org.ktm.web.form;

public class FrmSupplier extends FrmDomain {

    private static final long serialVersionUID = 5319306551811340277L;

    private String            oper             = "";
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
    public int compareTo(FrmDomain obj) {
        FrmSupplier other = (FrmSupplier) obj;
        return identifier.compareTo(other.identifier);
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
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
