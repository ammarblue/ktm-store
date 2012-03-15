package org.ktm.web.form;

public class FrmPerson extends FrmParty {

    private static final long serialVersionUID = 1L;

    // person info
    private String            prename;
    private String            firstname;
    private String            lastname;
    private String            birthDay;
    private String            emailAddress;
    private String            tel;

    public FrmPerson() {
        super();
        setPrename("");
        setFirstname("");
        setLastname("");
        setBirthDay(null);
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

    @Override
    public int compareTo(FrmDomain other) {
        FrmPerson person = (FrmPerson) other;
        return firstname.compareTo(person.firstname);
    }

}
