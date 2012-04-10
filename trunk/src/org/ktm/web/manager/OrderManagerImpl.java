package org.ktm.web.manager;

import java.io.Serializable;
import java.util.List;
import org.ktm.web.form.FrmDomain;
import org.ktm.web.form.FrmOrder;
import org.ktm.web.form.FrmOrderLine;

public class OrderManagerImpl extends FrmManagerAbstractImpl implements OrderManager {

    private static OrderManager theInstance = null;

    public static FormManager getInstance() {
        if (theInstance == null) {
            theInstance = new OrderManagerImpl();
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

    @Override
    public List<FrmOrderLine> getOrderLine(FrmOrder frmOrder) {
        // TODO Auto-generated method stub
        return null;
    }

}
