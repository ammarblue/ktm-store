package org.ktm.dao;

public enum EDatabaseSystem {
    MYSQL(0), HIBERNATE(1), MONGODB(2);

    protected final int id;

    private EDatabaseSystem(int id) {
        this.id = id;
    }

    public static EDatabaseSystem get(int id) {
        if (id == 1)
            return HIBERNATE;
        if (id == 2)
            return MONGODB;
        return MYSQL;
    }
}
