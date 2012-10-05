package org.ktm.dao;

import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.ktm.core.KTMContext;
import org.ktm.domain.KTMEntity;
import org.ktm.exception.DeleteException;
import com.mongodb.DB;
import com.mongodb.Mongo;

public abstract class DaoImplMangoDB extends DaoImpl {

    private static final long serialVersionUID = 1L;

    private static DB         mongoDB          = null;

    public DB getCurrentDB() {
        try {
            if (mongoDB == null) {
                Mongo m;
                m = new Mongo("localhost", 27017);
                mongoDB = m.getDB(KTMContext.databaseName);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return mongoDB;
    }

    @Override
    public CrudAdmin getCrudAdmin() {
        return null;
    }

    @Override
    public boolean isManaged(Object entity) {
        return false;
    }

    @Override
    public abstract Class<?> getFeaturedClass();

    public KTMEntity get(Class<?> entityClass, Serializable id) {
        KTMEntity object = null;
        return object;
    }

    @Override
    public int delete(KTMEntity object) throws DeleteException {
        int result = 0;
        return result;
    }

    public Collection<?> findAll(Class<?> entityClass) {
        List<KTMEntity> result = new ArrayList<KTMEntity>();
        return result;
    }
}
