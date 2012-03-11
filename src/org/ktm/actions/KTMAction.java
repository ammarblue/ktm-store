package org.ktm.actions;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public abstract class KTMAction extends AbstractCRUDAction {

    private static final long  serialVersionUID = 4025001861137214635L;

    public static final String SUCCESS          = "success";

    private boolean            isOwnerRead      = false;

    @SessionTarget
    protected Session          hbmSession;

    @TransactionTarget
    protected Transaction      transaction;

    public void lock() {
        isOwnerRead = true;
    }

    public void unlock() {
        isOwnerRead = false;
    }

    public boolean isOwner() {
        return isOwnerRead;
    }

    protected boolean isEmpty(String str) {
        if (str != null) {
            return str.isEmpty();
        }
        return true;
    }

    public Session getKTMSession() {
        if (isOwner()) {
            return hbmSession;
        }
        return null;
    }

    public Transaction getKTMTransatcion() {
        if (isOwner()) {
            return transaction;
        }
        return null;
    }
}
