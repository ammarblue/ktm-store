package org.ktm.tag.auth;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import org.ktm.domain.party.Party;

public class IsUserTagsImpl {

    public static boolean isUserInRoles(PageContext pageContext, Collection<String> roles) throws AuthException {
        ServletRequest sr = pageContext.getRequest();
        HttpServletRequest hr = (HttpServletRequest) sr;

        // the session context is not destroyed untill the request
        // is compleatly serviced so we need to check!
        if (AuthenticatorFactory.isUserLoggingOut()) {
            return false;
        }

        Iterator<String> iterator = roles.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            if (object instanceof String) {
                if (hr.isUserInRole((String) object)) {
                    return true;
                }
            }
        }

        return AuthenticatorFactory.isUserInRoles(roles);
    }

    public static boolean isUserInAllRoles(PageContext pageContext, Collection<String> roles) throws AuthException {
        Collection<String> rolesCopy = (Collection<String>) new Vector<String>(roles);
        Iterator<String> it = rolesCopy.iterator();
        while (it.hasNext()) {
            String object = it.next();
            Vector<String> tmpVector = new Vector<String>(1);
            tmpVector.add(object);
            if (isUserInRoles(pageContext, tmpVector)) {
                rolesCopy.remove(object);
            }
        }
        if (rolesCopy.isEmpty()) {
            return true;
        }

        return false;
    }

    public static boolean isUserNotInRoles(PageContext pageContext, Collection<String> roles) throws AuthException {
        if (isUserInRoles(pageContext, roles)) {
            return false;
        }

        return true;
    }

    public static boolean isUserNotInAllRoles(PageContext pageContext, Collection<String> roles) throws AuthException {
        if (isUserInAllRoles(pageContext, roles)) {
            return false;
        }

        return true;
    }

    public static boolean isUserNotInRole(PageContext pageContext, String theRole) throws AuthException {
        if (isUserInRole(pageContext, theRole)) {
            return false;
        }
        return true;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static boolean isUserInRole(PageContext pageContext, String theRole) throws AuthException {
        Vector tmpVector = new Vector(1);
        tmpVector.add(theRole);
        return isUserInRoles(pageContext, tmpVector);
    }

    public static boolean isUserLogedIn(PageContext pageContext) throws AuthException {
        return AuthenticatorFactory.isUserLoggedIn();
    }

    public static Party getParty(PageContext pageContext) throws AuthException {
        if (AuthenticatorFactory.isUserLoggedIn()) {
            return AuthenticatorFactory.getParty();
        }
        return null;
    }
}
