package org.ktm.web.manager;

import java.util.List;
import org.ktm.web.form.FrmProductInstance;

public interface InventoryEntryManager extends FormManager {

    public void addProductInstance(FrmProductInstance productInstance);

    // public void addProductInstance(KTMAction action, String
    // productIdentifier, String orderIdentifier, Integer quanity, Date time);

    public void removeProductInstance(FrmProductInstance productInstance);

    public List<FrmProductInstance> getProductInstances();

    public Integer getNumberAvailable();

}
