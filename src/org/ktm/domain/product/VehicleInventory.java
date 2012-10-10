package org.ktm.domain.product;

import javax.persistence.Entity;

@Entity
public class VehicleInventory extends MovingInventory {

    private static final long serialVersionUID = 1L;

    private String            vehicleRegistration;
    private String            modelName;
    private String            ownerName;

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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

}
