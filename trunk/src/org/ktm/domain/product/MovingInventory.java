package org.ktm.domain.product;

import javax.persistence.Entity;

@Entity
public class MovingInventory extends Inventory {

    private static final long serialVersionUID = 1L;

    private String            modelName;
    private String            vehicleRegistration;
    private String            ownerName;

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

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

}
