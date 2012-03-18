package org.ktm.actions;

public abstract class KTMAction extends AbstractCRUDAction {

    private static final long  serialVersionUID = 4025001861137214635L;

    public static final String SUCCESS          = "success";

    protected boolean isEmpty(String str) {
        if (str != null) {
            return str.isEmpty();
        }
        return true;
    }

}
