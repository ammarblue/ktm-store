package org.ktm.web.form;

public class FrmAuthen extends FrmDomain {

    private static final long serialVersionUID = 2593374568947232479L;

    private String            prename;
    private String            firstname;
    private String            lastname;
    private String            username;
    private String            password;
    private String            confirm;

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

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    @Override
    public int compareTo(FrmDomain o) {
        FrmAuthen authen = (FrmAuthen) o;
        return firstname.compareTo(authen.firstname);
    }

}
