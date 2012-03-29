package org.ktm.web.form;

public class FrmInventory extends FrmDomain {

    private static final long serialVersionUID = -6498845175834308471L;

    private String            identifier;
    private String            name;
    private String            modelName;
    private String            inventoryType;
    private String            vehicleRegistration;
    private String            ownerName;

    @Override
    public int compareTo(FrmDomain other) {
        FrmInventory obj = (FrmInventory) other;
        return identifier.compareTo(obj.identifier);
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

    public String getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(String inventoryType) {
        this.inventoryType = inventoryType;
    }

}
