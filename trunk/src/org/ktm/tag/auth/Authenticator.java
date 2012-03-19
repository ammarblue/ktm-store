package org.ktm.tag.auth;

import java.util.Collection;
import javax.servlet.ServletContext;
import org.ktm.domain.party.Party;

public interface Authenticator {

    public final static String PROP_USERNAME       = "org.ktm.login.userName";

    public final static String PROP_PASSWORD       = "org.ktm.login.password";

    public final static String SESSION_CONTEXT_KEY = "org.ktm.login.SessionContextKey";

    public void initialize(ServletContext context) throws AuthException;

    public void doLogin(String userName, String password) throws AuthException;

    public void doLogin() throws AuthException;

    public void doLogout() throws AuthException;

    public int getState();

    public Party getParty();

    public boolean isInRoles(Collection<?> roles);

    public boolean isInAllRoles(Collection<?> roles);

    public boolean isUserLoggedIn();

    public Collection<?> getRoles();

    public Object getProperty(String key);

    public void setProperty(String key, Object property);

    public void removeProperty(String key);
}
