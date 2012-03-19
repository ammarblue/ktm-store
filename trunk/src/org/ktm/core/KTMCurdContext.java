package org.ktm.core;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.ktm.actions.KTMAction;

public class KTMCurdContext extends KTMContext {

    private Session     session;
    private Transaction transaction;

    public KTMCurdContext(KTMAction action, Session session, Transaction transaction) {
        super(action);
        this.session = session;
        this.transaction = transaction;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

}
