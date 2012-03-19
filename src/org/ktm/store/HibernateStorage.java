package org.ktm.store;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.ktm.actions.KTMAction;
import org.ktm.core.KTMCurdContext;
import org.ktm.domain.KTMEntity;
import org.ktm.exception.CreateException;
import org.ktm.exception.DeleteException;
import org.ktm.exception.StorageException;
import org.ktm.exception.UpdateException;
import com.opensymphony.xwork2.ActionContext;

public class HibernateStorage implements Storage {

    private static final long serialVersionUID = 2632519509739198476L;

    private synchronized Session getSession() {
        KTMCurdContext context = (KTMCurdContext) ActionContext.getContext().get(KTMAction.CURRENT_CONTEXT);
        return context.getSession();
    }

    private Transaction getTransaction() {
        KTMCurdContext context = (KTMCurdContext) ActionContext.getContext().get(KTMAction.CURRENT_CONTEXT);
        return context.getTransaction();
    }

    public KTMEntity get(Class<?> entityClass, Serializable id) {
        KTMEntity object = null;
        if (entityClass != null && id != null) {
            try {
                object = (KTMEntity) getSession().get(entityClass, id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return object;
    }

    public Serializable create(KTMEntity object) throws CreateException {
        if (object == null) {
            throw new CreateException("Either given class or object was null");
        }
        // if (object.getUniqueId() == null) {
        // throw new CreateException("Cannot store object with null id");
        // }
        // if (get(object.getClass(), object.getUniqueId()) != null) {
        // throw new
        // DuplicateKeyException("Object with this id already exists.");
        // }

        try {
            getSession().saveOrUpdate(object);
        } catch (Exception e) {
            getTransaction().rollback();
            throw new CreateException(e);
        }

        return object.getUniqueId();
    }

    public KTMEntity update(KTMEntity object) throws UpdateException {
        if (object == null) {
            throw new UpdateException("Cannot update null object.");
        }
        if (get(object.getClass(), object.getUniqueId()) == null) {
            throw new UpdateException("Object to update not found.");
        }

        try {
            getSession().saveOrUpdate(object);
        } catch (Exception e) {
            getTransaction().rollback();
            throw new UpdateException(e);
        }
        return object;
    }

    public Serializable merge(KTMEntity object) throws StorageException {
        if (object == null) {
            throw new StorageException("Cannot merge null object");
        }
        if (object.getUniqueId() == null || get(object.getClass(), object.getUniqueId()) == null) {
            return create(object);
        } else {
            try {
                getSession().merge(object);
            } catch (Exception e) {
                getTransaction().rollback();
                throw new StorageException(e);
            }
        }
        return object.getUniqueId();
    }

    public int delete(Class<?> entityClass, Serializable id) throws DeleteException {
        int result = 0;
        try {
            if (get(entityClass, id) != null) {
                KTMEntity object = (KTMEntity) getSession().get(entityClass, id);
                getSession().delete(object);
                result = 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            getTransaction().rollback();
            throw new DeleteException(e);
        }
        return result;
    }

    public int delete(KTMEntity object) throws DeleteException {
        int result = 0;
        try {
            if (get(object.getClass(), object.getUniqueId()) != null) {
                getSession().delete(object);
                result = 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            getTransaction().rollback();
            throw new DeleteException(e);
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public Collection<?> findAll(Class<?> entityClass) {
        List<KTMEntity> object = null;
        try {
            object = getSession().createQuery(queryStringAllFromClass(entityClass)).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    private String queryStringAllFromClass(Class<?> entityClass) {
        return "from " + entityClass.getName();
    }

    @Override
    public Query getQuery(String queryString) {
        return getSession().createQuery(queryString);
    }

}
