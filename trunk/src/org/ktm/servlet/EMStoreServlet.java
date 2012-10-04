package org.ktm.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.ktm.stock.bean.FormBean;
import org.ktm.tags.MessageManager;
import org.ktm.utils.HibernateSessionUtil;
import org.ktm.utils.ServiceLocator;

public class EMStoreServlet extends SecureServlet {

    private static final long serialVersionUID = 1L;

    private static Logger     log              = Logger.getLogger(EMStoreServlet.class);

    public ActionForward handleStoreException(Exception exception) {
        log.error("message.store.failure");
        throw new RuntimeException(MessageManager.getString("hibernate.transaction.error"), exception);
    }

    @Override
    protected ActionForward processRequest(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<String> errors = new ArrayList<String>();
        ActionForward actionForward = null;

        Session hibernateSession = ServiceLocator.getCurrentSession();
        Transaction transaction = null;

        // Validate before store.
        try {
            // Validate modifications
            HibernateSessionUtil.validate(request, errors);
        } catch (HibernateException exception) {
            log.error("message.actionStore.failure");
            throw exception;
        }

        if (errors.isEmpty()) {
            // Validation was OK.
            try {
                transaction = hibernateSession.beginTransaction();
                transaction.begin();
                hibernateSession.flush();
                transaction.commit();
                // Clean up list of created objects
                resetCreatedCollection(session);
            } catch (HibernateException exception) {
                if (transaction != null) {
                    transaction.rollback();
                }
                // actionForward = handleStoreException(mapping, request,
                // errors, dataAccessException, hibernateSession);
            }
        } else {
            // Save validation errors for display on the JSP page
            saveErrors(request, errors);
            // actionForward = mapping.findForward("failures");
        }

        // request.setAttribute(Globals.KEY_BACKWARD, Globals.ANYVALUE);

        if (actionForward == null) {
            // actionForward = mapping.findForward(EVENT_success);
        }

        return actionForward;
    }

    private void saveErrors(HttpServletRequest request, List<String> errors) {
        // TODO Auto-generated method stub

    }

    private void resetCreatedCollection(HttpSession session) {
        // TODO Auto-generated method stub

    }
}
