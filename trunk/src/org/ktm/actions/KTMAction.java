package org.ktm.actions;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public abstract class KTMAction extends AbstractCRUDAction {

    private static final long  serialVersionUID = 4025001861137214635L;

    public static final String SUCCESS          = "success";

    @SessionTarget
    public Session             hbmSession;

    @TransactionTarget
    public Transaction         transaction;

    protected boolean isEmpty(String str) {
        if (str != null) {
            return str.isEmpty();
        }
        return true;
    }

}
