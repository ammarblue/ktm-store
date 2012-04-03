package org.ktm.web.form;

public class FrmCustomer extends FrmPerson {

    private static final long serialVersionUID = 1L;

    private String            desc;

    @Override
    public int compareTo(FrmDomain obj) {
        if (obj instanceof FrmCustomer) {
            FrmCustomer form = (FrmCustomer) obj;
            return this.getIdentifier().compareTo(form.getIdentifier());
        } else {
            return this.getId().compareTo(obj.getId());
        }
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
