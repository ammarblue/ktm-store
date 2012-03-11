package org.ktm.web.manager;

public class ServiceLocator {

    public static ProductManager getProductManager() {
        return ProductManager.getInstance();
    }
    
    public static PersonManager getPersonManager() {
        return PersonManager.getInstance();
    }
    
    public static AuthenManager getAuthenManager() {
        return AuthenManager.getInstance();
    }
}
