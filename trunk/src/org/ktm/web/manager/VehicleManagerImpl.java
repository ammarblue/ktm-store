package org.ktm.web.manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.ktm.dao.KTMEMDaoFactory;
import org.ktm.dao.party.VehicleDao;
import org.ktm.web.form.FrmDomain;

public class VehicleManagerImpl extends FrmManagerAbstractImpl implements VehicleManager {

    private static VehicleManagerImpl theInstance = null;
    
    public static VehicleManager getInstance() {
        if (theInstance == null) {
            theInstance = new VehicleManagerImpl();
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
        List<FrmDomain> result = new ArrayList<FrmDomain>();
        VehicleDao dao = KTMEMDaoFactory.getInstance().getVehicleDao();
        if (dao != null) {
            Collection<?> objs = dao.findAll();
        }
        return result;
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
