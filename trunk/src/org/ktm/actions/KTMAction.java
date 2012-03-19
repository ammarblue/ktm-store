package org.ktm.actions;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.ktm.core.KTMCurdContext;
import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.opensymphony.xwork2.ActionContext;

public abstract class KTMAction extends AbstractCRUDAction {

    private static final long  serialVersionUID = 4025001861137214635L;

    public static final String SUCCESS          = "success";

    public static final String CURRENT_CONTEXT  = KTMAction.class.getName() + "_context";

    @SessionTarget
    public Session             hbmSession;

    @TransactionTarget
    public Transaction         transaction;
    
    protected void initContext() {
        KTMCurdContext context = new KTMCurdContext(this, hbmSession, transaction);
        ActionContext.getContext().put(CURRENT_CONTEXT, context);
    }
}
