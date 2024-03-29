package org.ktm.stock.login;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.ktm.authen.Authenticator;
import org.ktm.dao.party.AuthenDao;
import org.ktm.dao.party.PartyRoleDao;
import org.ktm.domain.party.Authen;
import org.ktm.exception.AuthException;
import org.ktm.exception.KTMFailedLoginException;
import org.ktm.stock.dao.KTMEMDaoFactory;

public class AuthenImpl implements Authenticator {

    private final Logger                  log          = Logger.getLogger(AuthenImpl.class);

    private final int                     state        = 0;
    private boolean                       userLoggedIn = false;
    private final HashMap<String, Object> properties;
    private User                          currentUser;
    protected ServletContext              servletContext;

    public AuthenImpl() {
        properties = new HashMap<String, Object>();
    }

    @Override
    public void initialize(ServletContext context) throws AuthException {
        log.info("AuthImpl.initialize run");
        servletContext = context;
    }

    @Override
    public void doLogin(HttpServletRequest request) throws AuthException {
        this.doLogin(request, (String) this.getProperty(Authenticator.PROP_USERNAME), (String) this.getProperty(Authenticator.PROP_PASSWORD));
    }

    @Override
    public void doLogin(HttpServletRequest request, String username, String password) throws AuthException {
        Authen authen = null;

        log.info("Enter AuthenImpl");

        if ((username == null) | (password == null)) {
            this.removeProperty(Authenticator.PROP_PASSWORD);
            this.removeProperty(Authenticator.PROP_USERNAME);
            throw new KTMFailedLoginException();
        }

        // if (username.equals("keng") &&
        // password.equals("6e28dec34f04854dd34e527d7d1a79b605bdd085")) {
        // User u = new User();
        // Vector<String> v = new Vector<String>();
        // v.add("Root");
        // u.setRoles(v);
        // this.setProperty(Authenticator.PROP_PASSWORD, password);
        // this.setProperty(Authenticator.PROP_USERNAME, username);
        // this.currentUser = u;
        //
        // this.setUserLoggedIn(true);
        // return;
        // }

        try {
            boolean foundUser = false;

            username = username.trim();
            password = password.trim();

            AuthenDao authenDao = KTMEMDaoFactory.getInstance().getAuthenDao();

            log.info("auth.findByPrimaryKey(key)...");
            authen = authenDao.findByUsername(username);
            log.info("auth.findByPrimaryKey(key) done.");

            if (authen != null) {
                String passwd = authen.getPassword();
                if (password.equals(passwd)) {
                    foundUser = true;
                }
            }

            if (foundUser) {
                log.info("Found user");
                User usr = new User();
                this.setProperty(Authenticator.PROP_PASSWORD, password);
                this.setProperty(Authenticator.PROP_USERNAME, username);
                this.currentUser = usr;

                PartyRoleDao roleDao = KTMEMDaoFactory.getInstance().getPartyRoleDao();
                Vector<String> lst = roleDao.findByPartyString(authen.getParty());
                if (lst.size() > 0) {
                    usr.setRoles(lst);
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

        throw new KTMFailedLoginException();
    }

    @Override
    public void doLogout() throws AuthException {
        this.removeProperty(Authenticator.PROP_PASSWORD);
        this.removeProperty(Authenticator.PROP_USERNAME);
        this.setUserLoggedIn(false);
    }

    private void setUserLoggedIn(boolean loggedIn) {
        this.userLoggedIn = loggedIn;
    }

    @Override
    public boolean isUserLoggedIn() {
        return userLoggedIn;
    }

    @Override
    public Object getProperty(String key) {
        return properties.get(key);
    }

    @Override
    public void setProperty(String key, Object property) {
        properties.put(key, property);
    }

    @Override
    public void removeProperty(String key) {
        properties.remove(key);
    }

    @Override
    public int getState() {
        return state;
    }

    @Override
    public Collection<?> getRoles() {
        return this.currentUser.getRoles();
    }

    @Override
    public boolean isInAllRoles(Collection<?> roles) {
        if ((roles == null) || roles.isEmpty()) { return false; }
        Collection<?> userRoles = this.getRoles();
        if ((userRoles == null) || userRoles.isEmpty()) { return false; }
        if (userRoles.containsAll(roles)) { return true; }
        return false;
    }

    @Override
    public boolean isInRoles(Collection<?> roles) {
        if ((roles == null) || roles.isEmpty()) { return true; }
        Collection<?> userRoles = this.getRoles();
        if ((userRoles == null) || userRoles.isEmpty()) { return false; }

        Iterator<?> iterator = roles.iterator();
        while (iterator.hasNext()) {
            String role = (String) iterator.next();
            if (userRoles.contains(role)) { return true; }
        }
        return false;
    }

    private class User {
        protected Vector<?> roles;

        void setRoles(Vector<?> roles) {
            this.roles = roles;
        }

        Vector<?> getRoles() {
            return this.roles;
        }

    }
}
