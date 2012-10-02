package org.ktm.store;

import java.io.Serializable;
import java.util.Collection;
import org.ktm.domain.KTMEntity;
import org.ktm.exception.CreateException;
import org.ktm.exception.DeleteException;
import org.ktm.exception.StorageException;
import org.ktm.exception.UpdateException;

public class MongoStorage extends Storage {

    private static final long serialVersionUID = 1L;

    @Override
    public KTMEntity get(Class<?> entityClass, Serializable id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Serializable create(KTMEntity object) throws CreateException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public KTMEntity update(KTMEntity object) throws UpdateException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Serializable merge(KTMEntity object) throws StorageException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int delete(Class<?> entityClass, Serializable id) throws DeleteException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int delete(KTMEntity object) throws DeleteException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Collection<?> findAll(Class<?> entityClass) {
        // TODO Auto-generated method stub
        return null;
    }

}
