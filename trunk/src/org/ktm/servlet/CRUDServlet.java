package org.ktm.servlet;

import org.apache.log4j.Logger;
import org.ktm.utils.Localizer;

public class CRUDServlet extends DispatchServlet {

    private static final long serialVersionUID = 1L;

    private static Logger     log              = Logger.getLogger(CRUDServlet.class);

    public ActionForward handleStoreException(Exception exception) {
        log.error("message.store.failure");
        throw new RuntimeException(Localizer.getString("hibernate.transaction.error"), exception);
    }

    /*
     * protected boolean store(HttpServletRequest request, HttpServletResponse
     * response) throws ServletException, IOException { HttpSession session =
     * request.getSession(); List<String> errors = new ArrayList<String>();
     * boolean result = false;
     * 
     * Session hibernateSession = ServiceLocator.getCurrentSession();
     * Transaction transaction = null;
     * 
     * // Validate before store. try { // Validate modifications
     * HibernateSessionUtil.validate(request, errors); } catch
     * (HibernateException exception) {
     * log.error("message.actionStore.failure"); throw exception; }
     * 
     * if (errors.isEmpty()) { // Validation was OK. try { transaction =
     * hibernateSession.beginTransaction(); transaction.begin();
     * hibernateSession.flush(); transaction.commit(); // Clean up list of
     * created objects resetCreatedCollection(session); } catch
     * (HibernateException exception) { if (transaction != null) {
     * transaction.rollback(); } exception.printStackTrace(); // actionForward =
     * handleStoreException(mapping, request, // errors, dataAccessException,
     * hibernateSession); } } else { // Save validation errors for display on
     * the JSP page saveErrors(request, errors); // actionForward =
     * mapping.findForward("failures"); }
     * 
     * // request.setAttribute(Globals.KEY_BACKWARD, Globals.ANYVALUE);
     * 
     * // if (actionForward == null) { // actionForward =
     * mapping.findForward(EVENT_success); // }
     * 
     * return result; }
     * 
     * private void saveErrors(HttpServletRequest request, List<String> errors)
     * {
     * 
     * }
     * 
     * private void resetCreatedCollection(HttpSession session) {
     * 
     * }
     */
}
