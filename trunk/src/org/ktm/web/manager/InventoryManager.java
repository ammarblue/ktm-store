package org.ktm.web.manager;

import java.util.List;
import org.ktm.web.form.FrmInventoryEntry;
import org.ktm.web.form.FrmProductInstance;
import org.ktm.web.form.FrmProductType;

public interface InventoryManager extends FormManager {
    /*
     * Adds an InventoryEntry representing a ProductType to the Inventory
     */
    public void addInventoryEntry(FrmInventoryEntry inventoryEntry);

    /*
     * Removes an InventoryEntry from the Inventory This happens when the
     * business decides to no longer sell a particular ProductType
     */
    public void removeInventoryEntry(FrmInventoryEntry inventoryEntry);

    /*
     * Finds one or more InventoryEntries
     */
    public List<FrmInventoryEntry> findInventoryEntry(String productIdentifier);

    /*
     * Find all InventoryEntries
     */
    public List<FrmInventoryEntry> getInventoryEntries();
    
    /*
     * Find all ProductType
     */
    public List<FrmProductType> getProductTypes();

    /*
     * Adds new ProductInstances to the Inventory If an InventoryEntry for the
     * ProductType already exists, the new ProductInstances are simply added to
     * the existing InventoryEntry; otherwise, a new InventoryEntry is first
     * added for the new ProductType
     */
    public void addProductInstance(FrmInventoryEntry inventoryEntry, FrmProductInstance product);

    /*
     * Removes a ProductInstance from the Inventory when it is sold
     * The number of ProductInstance in the appropriate
     * InventoryEntry is decremented
     */
    public void removeProductInstance(FrmInventoryEntry inventoryEntry, FrmProductInstance product, Integer amount);
    
    /*
     * Finds one or more ProductInstances of a particular ProductType
     * you have available or what your current capacity to offer a
     * specific ServiceInstance is (ServiceInstance is a type of ProductInstance)
     */
    public FrmProductInstance findProductInstance(Integer id);
    
    /*
     * Counts how many ProductInstances of a particular ProductType you have
     * available or what your current capacity to offer a specific ServiceInstance is 
     * (ServiceInstance is a type of ProductInstance)
     */
    public Integer getNumberOfProductInstancesAvailable(FrmProductType productType);
}
