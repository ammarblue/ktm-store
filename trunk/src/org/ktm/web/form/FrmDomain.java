package org.ktm.web.form;

import java.io.Serializable;

public abstract class FrmDomain implements Serializable, Comparable<FrmDomain> {

    private static final long serialVersionUID = 1L;

    private String            id;
    private boolean           isNew;

    public FrmDomain() {
        setNew(false);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }

}
