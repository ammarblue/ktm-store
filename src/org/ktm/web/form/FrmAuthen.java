package org.ktm.web.form;

public class FrmAuthen extends FrmDomain {

    private static final long serialVersionUID = 2593374568947232479L;

    private String            preName;
    private String            firstName;
    private String            lastName;
    private String            username;
    private String            password;
    private String            confirm;

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

    @Override
    public int compareTo(FrmDomain o) {
        FrmAuthen authen = (FrmAuthen) o;
        return firstName.compareTo(authen.firstName);
    }

}
