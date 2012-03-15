package org.ktm.web.manager;

import java.util.List;
import org.ktm.actions.KTMAction;
import org.ktm.web.form.FrmProductInstance;

public interface InventoryEntryManager extends FormManager {

    public void addProductInstance(KTMAction action, FrmProductInstance productInstance);
    
    //public void addProductInstance(KTMAction action, String productIdentifier, String orderIdentifier, Integer quanity, Date time);
    
    public void removeProductInstance(KTMAction action, FrmProductInstance productInstance);
    
    public List<FrmProductInstance> getProductInstances(KTMAction action);
    
    public Integer getNumberAvailable(KTMAction action);
    
}
