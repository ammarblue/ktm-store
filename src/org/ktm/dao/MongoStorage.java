package org.ktm.dao;

import java.io.Serializable;
import java.util.Collection;
import org.ktm.domain.KTMEntity;
import org.ktm.exception.CreateException;
import org.ktm.exception.DeleteException;
import org.ktm.exception.StorageException;
import org.ktm.exception.UpdateException;
import org.ktm.utils.CrudAdmin;

public class MongoStorage extends DaoImpl {

    private static final long serialVersionUID = 1L;

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

    public int delete(Class<?> entityClass, Serializable id) throws DeleteException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int delete(KTMEntity object) throws DeleteException {
        // TODO Auto-generated method stub
        return 0;
    }

    public Collection<?> findAll(Class<?> entityClass) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Class<?> getFeaturedClass() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CrudAdmin getCrudAdmin() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isManaged(Object entity) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public KTMEntity get(Serializable id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int delete(Serializable id) throws DeleteException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Collection<?> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

}
