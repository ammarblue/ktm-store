package org.ktm.web.form;

public class FrmParty extends FrmDomain {

    private static final long serialVersionUID = 1L;

    private String            identifier;
    private String            registeredIdentifier;
    // address
    private String            emailAddress;
    private String            tel;

    public FrmParty() {
        setIdentifier("");
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Integer getVersion() {
        return null;
    }

    public void setVersion(Integer version) {

    }

    public String getRegisteredIdentifier() {
        return registeredIdentifier;
    }

    public void setRegisteredIdentifier(String registeredIdentifier) {
        this.registeredIdentifier = registeredIdentifier;
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

    @Override
    public int compareTo(FrmDomain o) {
        FrmParty party = (FrmParty) o;
        return this.getIdentifier().compareTo(party.getIdentifier());
    }

}
