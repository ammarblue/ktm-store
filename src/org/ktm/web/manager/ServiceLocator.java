package org.ktm.web.manager;

public class ServiceLocator {
    
    public static PersonManager getPersonManager() {
        return PersonManagerImpl.getInstance();
    }
    
    public static AuthenManager getAuthenManager() {
        return AuthenManagerImpl.getInstance();
    }

    public static ProductCatalogManager getProductCatalogManager() {
        return ProductCatalogManagerImpl.getInstance();
    }
    
    public static BeveragePackageManager getBeveragePackageManager() {
        return BeveragePackageManagerImpl.getInstance();
    }

    public static SupplierManager getSupplierManager() {
        return SupplierManagerImpl.getInstance();
    }

    public static InventoryManager getInventoryManager() {
        return InventoryManagerImpl.getInstance();
    }

    public static InventoryManager getFixedInventoryManager() {
        return FixedInventoryManagerImpl.getInstance();
    }

    public static InventoryManager getMovingInventoryManager() {
        return MovingInventoryManagerImpl.getInstance();
    }
}
