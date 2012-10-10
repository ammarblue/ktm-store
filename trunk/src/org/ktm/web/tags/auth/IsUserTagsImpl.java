package org.ktm.web.tags.auth;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

public class IsUserTagsImpl {

    public static boolean isUserInRoles(PageContext pageContext, Collection<String> roles) {
        ServletRequest sr = pageContext.getRequest();
        HttpServletRequest hr = (HttpServletRequest) sr;

        // the session context is not destroyed untill the request
        // is compleatly serviced so we need to check!
        if (AuthenticatorFactory.isUserLoggingOut(hr)) {
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

        return AuthenticatorFactory.isUserInRoles(hr, roles);
    }

    public static boolean isUserInAllRoles(PageContext pageContext, Collection<String> roles) {
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

    public static boolean isUserNotInRoles(PageContext pageContext, Collection<String> roles) {
        if (isUserInRoles(pageContext, roles)) {
            return false;
        }

        return true;
    }

    public static boolean isUserNotInAllRoles(PageContext pageContext, Collection<String> roles) {
        if (isUserInAllRoles(pageContext, roles)) {
            return false;
        }

        return true;
    }

    public static boolean isUserNotInRole(PageContext pageContext, String theRole) {
        if (isUserInRole(pageContext, theRole)) {
            return false;
        }
        return true;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static boolean isUserInRole(PageContext pageContext, String theRole) {
        Vector tmpVector = new Vector(1);
        tmpVector.add(theRole);
        return isUserInRoles(pageContext, tmpVector);
    }

    public static boolean isUserLogedIn(PageContext pageContext) {
        HttpServletRequest hr = (HttpServletRequest) pageContext.getRequest();;
        return AuthenticatorFactory.isUserLoggedIn(hr);
    }
}
