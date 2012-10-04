package org.ktm.utils;

import org.hibernate.classic.Session;
import org.ktm.exception.ConstraintException;

/**
 * 
 * @author Mr.Watcharin Sarachai
 * 
 *         SessionContext instances faciliate client code in their session /
 *         transaction / validation demarcation.
 * @see com.kg.dev.hibernate.persistence.SessionTransactionWrapper
 */
public class SessionContext {

    /**
     * Remember if this SessionContext is a session-transaction initiator.
     */
    private boolean                   isSessionTransactionInitiator;

    /**
     * A TransactionWrapper for making callbacks to.
     */
    private SessionTransactionWrapper sessionTransactionWrapper;

    /**
     * Private constructor, in order to force parameterized construction.
     */
    @SuppressWarnings("unused")
    private SessionContext() {

    }

    /**
     * Constructor, parameterized with information whether or not this instance
     * is a session-transaction initiator, and equipped with a
     * SessionTransactionWrapper for making callbacks to.
     * 
     * @param isSessionTransactionInitiator
     *            defines whether or not this instance is a session-transaction
     *            initiator.
     * @param sessionTransactionWrapper
     *            a SessionTransactionWrapper for making callbacks to.
     */
    SessionContext(boolean isSessionTransactionInitiator, SessionTransactionWrapper sessionTransactionWrapper) {
        this.isSessionTransactionInitiator = isSessionTransactionInitiator;
        this.sessionTransactionWrapper = sessionTransactionWrapper;
    }

    /**
     * Get the underlying SessionTransactionWrapper's session.
     * 
     * @return underlying SessionTransactionWrapper's session.
     */
    public Session getSession() {
        return sessionTransactionWrapper.getSession();
    }

    /**
     * Mark the underlying SessionTransactionWrapper for validation.
     */
    public void markForValidation() {
        sessionTransactionWrapper.markForValidation();
    }

    /**
     * Mark the underlying SessionTransactionWrapper for rollback. If this
     * session context is a session-transaction owner: also close the underlying
     * SessionTransactionWrapper.
     */
    public void markForRollback() {
        sessionTransactionWrapper.markForRollback();

        if (isSessionTransactionInitiator) {
            sessionTransactionWrapper.close();
        }
    }

    /**
     * If this session context is a session-transaction owner: validate and
     * close the underlying SessionTransactionWrapper.
     */
    public void close() throws ConstraintException {
        if (isSessionTransactionInitiator) {
            sessionTransactionWrapper.validateAndClose();
        }
    }
}
