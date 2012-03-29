package org.ktm.actions.party;

import java.util.StringTokenizer;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.ktm.actions.CrudAction;
import org.ktm.web.form.FrmInventory;
import org.ktm.web.manager.ServiceLocator;
import org.ktm.web.manager.SupplierManager;

public class CrudGridVehicleInventory extends CrudAction {

    private static final long serialVersionUID = 3928207560159104965L;
    private Logger            log              = Logger.getLogger(CrudGridVehicleInventory.class);

    private String            oper             = "";
    private String            id;
    private String            identifier;
    private String            name;
    private String            modelName;
    private String            inventoryType;
    private String            vehicleRegistration;
    private String            ownerName;
    
    @Override
    protected SupplierManager getManager() {
        return ServiceLocator.getSupplierManager();
    }

    @Actions({ @Action(value = "/crud-grid-vehicle", results = { @Result(location = "simpleecho.jsp", name = "success"), @Result(location = "simpleecho.jsp", name = "input") }) })
    public String execute() throws Exception {
        log.debug("id: " + id);
        log.debug("identifier: " + identifier);

        initContext();

        if (oper.equalsIgnoreCase("del")) {
            StringTokenizer ids = new StringTokenizer(id, ",");
            while (ids.hasMoreTokens()) {
                int removeId = Integer.parseInt(ids.nextToken());
                log.debug("Delete productType " + removeId);
                getManager().delete(removeId);
            }
        } else {
            FrmInventory form = new FrmInventory();
            form.setIdentifier(identifier);

            if (oper.equalsIgnoreCase("add")) {
                log.debug("Add new Supplier");
                form.setNew(true);
            } else if (oper.equalsIgnoreCase("edit")) {
                log.debug("Edit Supplier");
                form.setId(Integer.parseInt(id));
                form.setNew(false);
            }
            
            getManager().addOrUpdate(form);
        }
        return SUCCESS;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(String inventoryType) {
        this.inventoryType = inventoryType;
    }

    public String getVehicleRegistration() {
        return vehicleRegistration;
    }

    public void setVehicleRegistration(String vehicleRegistration) {
        this.vehicleRegistration = vehicleRegistration;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    
}