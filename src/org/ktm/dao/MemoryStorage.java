package org.ktm.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.ktm.domain.KTMEntity;
import org.ktm.exception.CreateException;
import org.ktm.exception.DeleteException;
import org.ktm.exception.DuplicateKeyException;
import org.ktm.exception.StorageException;
import org.ktm.exception.UpdateException;

public class MemoryStorage extends DaoImpl {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("rawtypes")
    private Map<Class, Map>   memory           = new HashMap<Class, Map>();

    @SuppressWarnings("rawtypes")
    private Map getEntityMap(Class<?> entityClass) {
        if (entityClass != null) {
            Map tryMap = memory.get(entityClass);
            if (tryMap == null) {
                synchronized (memory) {
                    tryMap = new HashMap();
                    memory.put(entityClass, tryMap);
                }
            }
            return tryMap;
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private KTMEntity intStore(Class<?> entityClass, KTMEntity object) {
        getEntityMap(entityClass).put(object.getUniqueId(), object);
        return object;
    }

    public KTMEntity get(Class<?> entityClass, Serializable id) {
        if (entityClass != null && id != null) {
            return (KTMEntity) getEntityMap(entityClass).get(id);
        } else {
            return null;
        }
    }

    @Override
    public Serializable create(KTMEntity object) throws CreateException {
        if (object == null) {
            throw new CreateException("Either given class or object was null");
        }
        if (object.getUniqueId() == null) {
            throw new CreateException("Cannot store object with null id");
        }
        if (get(object.getClass(), object.getUniqueId()) != null) {
            throw new DuplicateKeyException("Object with this id already exists.");
        }
        return intStore(object.getClass(), object).getUniqueId();
    }

    @Override
    public KTMEntity update(KTMEntity object) throws UpdateException {
        if (object == null) {
            throw new UpdateException("Cannot update null object.");
        }
        if (get(object.getClass(), object.getUniqueId()) == null) {
            throw new UpdateException("Object to update not found.");
        }
        return intStore(object.getClass(), object);
    }

    @Override
    public Serializable merge(KTMEntity object) throws StorageException {
        if (object == null) {
            throw new StorageException("Cannot merge null object");
        }
        if (object.getUniqueId() == null || get(object.getClass(), object.getUniqueId()) == null) {
            return create(object);
        } else {
            return update(object).getUniqueId();
        }
    }

    public int delete(Class<?> entityClass, Serializable id) throws DeleteException {
        try {
            if (get(entityClass, id) != null) {
                getEntityMap(entityClass).remove(id);
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            throw new DeleteException(e);
        }
    }

    @Override
    public int delete(KTMEntity object) throws DeleteException {
        if (object == null) {
            throw new DeleteException("Cannot delete null object");
        }
        return delete(object.getClass(), object.getUniqueId());
    }

    public Collection<?> findAll(Class<?> entityClass) {
        if (entityClass != null) {
            return getEntityMap(entityClass).values();
        } else {
            return new ArrayList<Object>();
        }
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void reset() {
        this.memory = new HashMap();
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
