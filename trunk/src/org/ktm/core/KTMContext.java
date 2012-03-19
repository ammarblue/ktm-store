package org.ktm.core;

import org.ktm.actions.KTMAction;

public class KTMContext {

    private KTMAction action;
    
    public KTMContext(KTMAction action) {
        this.action = action;
    }

    public KTMAction getAction() {
        return action;
    }

    public void setAction(KTMAction action) {
        this.action = action;
    }
    
}
