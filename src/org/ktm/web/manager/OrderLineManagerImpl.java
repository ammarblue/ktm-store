package org.ktm.web.manager;

import java.io.Serializable;
import java.util.List;
import org.ktm.web.form.FrmDomain;

public class OrderLineManagerImpl extends FrmManagerAbstractImpl implements OrderLineManager {

    private static OrderLineManager theInstance = null;

    public static FormManager getInstance() {
        if (theInstance == null) {
            theInstance = new OrderLineManagerImpl();
        }
        return theInstance;
    }
    
    @Override
    public FrmDomain get(Serializable tryId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<?> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer delete(Serializable tryId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer delete(FrmDomain toDelete) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer addOrUpdate(FrmDomain toAdd) {
        // TODO Auto-generated method stub
        return null;
    }

}
