package org.ktm.stock.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.ktm.domain.KTMEntity;
import org.ktm.domain.product.Inventory;
import org.ktm.domain.product.VehicleInventory;
import org.ktm.web.bean.FormBean;

public class InventoryBean extends FormBean {

    private List<InventoryBean> inventoryCollection = new ArrayList<InventoryBean>();

    private String              selectedInventoryType;
    private String              identifier;
    private String              name;

    // Vehicle
    private String              vehicleRegistration;
    private String              modelName;
    private String              ownerName;

    @Override
    public void reset() {
        this.setIdentifier("");
        this.setName("");
        this.setVehicleRegistration("");
        this.setModelName("");
        this.setOwnerName("");
    }

    @Override
    public void loadFormCollection(Collection<?> entitys) {
        inventoryCollection.clear();
        if (entitys != null && entitys.size() > 0) {
            for (Object obj : entitys) {
                if (obj instanceof Inventory) {
                    InventoryBean bean = new InventoryBean();
                    bean.loadToForm((KTMEntity) obj);
                    inventoryCollection.add(bean);
                }
            }
        }
    }

    @Override
    public void loadToForm(KTMEntity entity) {
        if (entity != null && entity instanceof Inventory) {
            Inventory inventory = (Inventory) entity;
            super.loadToForm(inventory);
            this.setIdentifier(inventory.getIdentifier());
            this.setName(inventory.getName());

            if (inventory instanceof VehicleInventory) {
                VehicleInventory vehicle = (VehicleInventory) inventory;
                this.setVehicleRegistration(vehicle.getVehicleRegistration());
                this.setMethod(vehicle.getModelName());
                this.setOwnerName(vehicle.getOwnerName());
            }
        }
    }

    @Override
    public void syncToEntity(KTMEntity entity) {
        if (entity != null && entity instanceof Inventory) {
            super.syncToEntity(entity);
            Inventory inventory = (Inventory) entity;
            inventory.setIdentifier(this.getIdentifier());
            inventory.setName(this.getName());
            if (entity instanceof VehicleInventory) {
                VehicleInventory vehicleInventory = (VehicleInventory) inventory;
                vehicleInventory.setOwnerName(this.getOwnerName());
                vehicleInventory.setModelName(this.getModelName());
                vehicleInventory.setVehicleRegistration(this.getVehicleRegistration());
            }
        }
    }

    public List<InventoryBean> getInventoryCollection() {
        return inventoryCollection;
    }

    public void setInventoryCollection(List<InventoryBean> inventoryCollection) {
        this.inventoryCollection = inventoryCollection;
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

    public String getSelectedInventoryType() {
        return selectedInventoryType;
    }

    public void setSelectedInventoryType(String selectedInventoryType) {
        this.selectedInventoryType = selectedInventoryType;
    }

    public String getVehicleRegistration() {
        return vehicleRegistration;
    }

    public void setVehicleRegistration(String vehicleRegistration) {
        this.vehicleRegistration = vehicleRegistration;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

}
