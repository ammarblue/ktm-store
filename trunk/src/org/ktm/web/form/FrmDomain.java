package org.ktm.web.form;

import java.io.Serializable;

public abstract class FrmDomain implements Serializable, Comparable<FrmDomain> {

    private static final long serialVersionUID = 1L;

    private Integer           id;
    private boolean           isNew;

    public FrmDomain() {
        setNew(false);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }

}
