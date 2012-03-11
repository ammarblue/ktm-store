package org.ktm.web.form;

public class FrmPerson extends FrmParty {

    private static final long serialVersionUID = 1L;

    // person info
    private String preName;
    private String firstName;
    private String lastName;
    private String birthDay;
    private String emailAddress;
    private String tel;

    public FrmPerson() {
        super();
        setPreName("");
        setFirstName("");
        setLastName("");
        setBirthDay(null);
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
        return firstName.compareTo(person.firstName);
    }

}
