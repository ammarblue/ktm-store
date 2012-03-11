package org.ktm.web.manager;

import java.io.Serializable;
import java.util.List;
import org.ktm.actions.KTMAction;
import org.ktm.web.form.FrmDomain;

public class ProductManager extends FrmManagerAbstractImpl {

    private static ProductManager theInstance = null;

    public static ProductManager getInstance() {
        if (theInstance == null) {
            theInstance = new ProductManager();
        }
        return theInstance;
    }

    @Override
    public synchronized List<FrmDomain> findAll(KTMAction action) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public synchronized Integer delete(KTMAction action, FrmDomain toDelete) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public synchronized FrmDomain get(KTMAction action, Serializable tryId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public synchronized Integer addOrUpdate(KTMAction action, FrmDomain toAdd) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer delete(KTMAction action, Serializable tryId) {
        // TODO Auto-generated method stub
        return null;
    }
}
