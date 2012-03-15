package org.ktm.web.manager;

public class ServiceLocator {

    public static InventoryManager getInventoryManager() {
        return InventoryManagerImpl.getInstance();
    }
    
    public static PersonManager getPersonManager() {
        return PersonManagerImpl.getInstance();
    }
    
    public static AuthenManager getAuthenManager() {
        return AuthenManagerImpl.getInstance();
    }

    public static ProductCatalogManager getProductCatalogManager() {
        return ProductCatalogManagerImpl.getInstance();
    }
}
