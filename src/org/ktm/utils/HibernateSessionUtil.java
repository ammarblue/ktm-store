package org.ktm.utils;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.classic.Session;
import org.hibernate.context.ManagedSessionContext;
import org.ktm.core.SessionWrapper;

public class HibernateSessionUtil {

    public static Session bindEntitySession(HttpServletRequest httpRequest) {
        HttpSession httpSession = httpRequest.getSession();
        SessionWrapper sessionWrapper = (SessionWrapper) httpSession.getAttribute(Globals.ENTITY_SESSION);

        if (sessionWrapper == null) {
            sessionWrapper = EMServiceLocator.getSessionWrapper();

            // Put session wrapper in HttpSession. Next request can re-use it.
            httpSession.setAttribute(Globals.ENTITY_SESSION, sessionWrapper);
        } else {
            // HttpSession already contains a session wrapper. Feed it to entity
            // service locator. The fed session wrapper becomes current.
            EMServiceLocator.setSessionWrapper(sessionWrapper);
        }

        Session session = sessionWrapper.getSession();

        ManagedSessionContext.bind(session);

        return session;
    }

    public static void unbindEntitySession(HttpServletRequest httpRequest, boolean forceClose) {
        HttpSession httpSession = httpRequest.getSession();
        SessionWrapper sessionWrapper = (SessionWrapper) httpSession.getAttribute(Globals.ENTITY_SESSION);

        if (sessionWrapper != null) {
            ManagedSessionContext.unbind(sessionWrapper.getSession().getSessionFactory());
            EMServiceLocator.clearSessionWrapper();

            if (forceClose || httpRequest.getAttribute(Globals.ENTITY_SESSION_END_OF_CONVERSATION) != null) {
                try {
                    sessionWrapper.getSession().close();
                } catch (HibernateException ex) {
                    // Close failed
                }
                httpSession.removeAttribute(Globals.ENTITY_SESSION);
            }
        }
    }

    public static void validate(HttpServletRequest request, List<String> errors) {
        /*
         * Locale locale = request.getLocale();
         * 
         * Validator validator = new ValidatorImplHibernate(); ValidationErrors
         * validationErrors = validator.validate();
         * 
         * Set objectsWithErrors = validationErrors.getObjectsWithErrors(); for
         * (Iterator it1 = objectsWithErrors.iterator(); it1.hasNext();) {
         * Object objectWithErrors = it1.next(); List errorList =
         * validationErrors.getErrorListForObject(objectWithErrors);
         * 
         * for (Iterator it2 = errorList.iterator(); it2.hasNext();) { // Class
         * ValidationErrors guarantees type-safety. ValidationError
         * validationError = (ValidationError) it2.next(); String
         * validationErrorMessage = validationError.getMessage(locale); // No
         * need for Struts to look-up Bundle. Error will produce locale-specific
         * message by itself. errors.add(ActionMessages.GLOBAL_MESSAGE, new
         * ActionMessage
         * ("message.actionUpdate.failure",validationErrorMessage)); } }
         */
    }

}
