package org.ktm.utils;

import org.hibernate.FlushMode;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.ktm.exception.ConstraintException;

public class SessionTransactionWrapper extends SessionWrapper {

    private static final long serialVersionUID              = 1L;

    private Transaction       transaction;

    private boolean           markedForRollback;

    private boolean           markedForValidation;

    private boolean           isSessionTransactionInitiator = true;

    @SuppressWarnings("unused")
    private SessionTransactionWrapper() {
    }

    public SessionTransactionWrapper(SessionFactory sessionFactory) {
        super(sessionFactory);
        transaction = getSession().beginTransaction();
    }

    public SessionContext buildSessionContext() {
        SessionContext sessionContext = new SessionContext(isSessionTransactionInitiator, this);
        isSessionTransactionInitiator = false;

        return sessionContext;
    }

    void markForRollback() {
        markedForRollback = true;
    }

    void markForValidation() {
        markedForValidation = true;
    }

    void validateAndClose() throws ConstraintException {
        // ConstraintException ace = null;

        try {
            if (getSession() != null && transaction != null && markedForValidation && !markedForRollback) {
                // ValidationErrors validationErrors = new
                // ValidatorImplHibernate().validate();

                // if (!validationErrors.isEmpty()) {
                // markedForRollback = true;
                // ConstraintErrors ktmConstraintErrors = new
                // ConstraintErrors();
                // ktmConstraintErrors.addLocalizedErrors(validationErrors.toList());
                // ace = new ConstraintException("ERR_ConstraintException",
                // ktmConstraintErrors);
                // }
            }
        } finally {
            close();
        }

        // if (ace != null) {
        // throw ace;
        // }
    }

    /**
     * Close this SessionTransactionWrapper, as follows.
     * <p>
     * First, clear this SessionTransactionWrapper from the current ThreadLocal
     * singleton.
     * </p>
     * <p>
     * Next, if this SessionTransactionWrapper was marked for rollback: rollback
     * the wrapped transaction. Else, commit the wrapped transaction. When
     * committing, first set the wrapped transaction's FlushMode explicitly to
     * COMMIT, as we are done with validating. This will ensure a true flush
     * will happen to the underlying datasource.
     * </p>
     * <p>
     * Finally, close the wrapped session.
     * </p>
     */
    public void close() {
        ServiceLocator.clearSessionWrapper();
        Session session = getSession();

        if (session != null && session.isOpen()) {
            try {
                if (transaction != null) {
                    if (markedForRollback) {
                        transaction.rollback();
                    } else {
                        session.setFlushMode(FlushMode.COMMIT);
                        transaction.commit();
                    }
                }
            } finally {
                session.close();
            }
        }
    }
}
