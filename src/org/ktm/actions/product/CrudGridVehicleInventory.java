package org.ktm.actions.product;

import java.util.StringTokenizer;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.ktm.actions.CrudAction;
import org.ktm.web.form.FrmInventory;
import org.ktm.web.manager.InventoryManager;
import org.ktm.web.manager.ServiceLocator;

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
    private String            type;
    
    @Override
    protected InventoryManager getManager() {
        if (type.equals("Fixed")) {
            return ServiceLocator.getFixedInventoryManager();
        } else {
            return ServiceLocator.getMovingInventoryManager();
        }
    }
    
    public String execute(FrmInventory form) throws Exception {
        initContext();
        
        if (oper.equalsIgnoreCase("del")) {
            StringTokenizer ids = new StringTokenizer(id, ",");
            while (ids.hasMoreTokens()) {
                int removeId = Integer.parseInt(ids.nextToken());
                log.debug("Delete productType " + removeId);
                getManager().delete(removeId);
            }
        } else {
            if (oper.equalsIgnoreCase("add")) {
                log.debug("Add new Supplier");
                form.setNew(true);
            } else if (oper.equalsIgnoreCase("edit")) {
                log.debug("Edit Supplier");
                form.setId(Integer.parseInt(id));
                form.setNew(false);
            }
        }
        
        getManager().addOrUpdate(form);
        
        return SUCCESS;
    }

    @Actions({ @Action(value = "/crud-grid-stock", results = { @Result(location = "simpleecho.jsp", name = "success"), @Result(location = "simpleecho.jsp", name = "input") }) })
    public String executeStock() throws Exception {
        log.debug("id: " + id);
        log.debug("identifier: " + identifier);
        log.debug("name: " + name);
        
        FrmInventory form = new FrmInventory();
        form.setIdentifier(identifier);
        form.setName(name);
        form.setInventoryType("FixedInventory");
            
        return execute(form);
    }
    
    @Actions({ @Action(value = "/crud-grid-vehicle", results = { @Result(location = "simpleecho.jsp", name = "success"), @Result(location = "simpleecho.jsp", name = "input") }) })
    public String executeVehicel() throws Exception {
        log.debug("id: " + id);
        log.debug("identifier: " + identifier);
        log.debug("name: " + name);
        log.debug("modelName: " + modelName);
        log.debug("vehicleRegistration: " + vehicleRegistration);
        log.debug("ownerName: " + ownerName);
        
        FrmInventory form = new FrmInventory();
        form.setIdentifier(identifier);
        form.setName(name);
        form.setModelName(modelName);
        form.setVehicleRegistration(vehicleRegistration);
        form.setOwnerName(ownerName);
        form.setInventoryType("MovingInventory");

        return execute(form);
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}