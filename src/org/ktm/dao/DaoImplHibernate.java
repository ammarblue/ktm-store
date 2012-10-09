package org.ktm.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.ktm.domain.KTMEntity;
import org.ktm.exception.CreateException;
import org.ktm.exception.DeleteException;
import org.ktm.exception.StorageException;
import org.ktm.exception.UpdateException;
import org.ktm.utils.ServiceLocator;

public abstract class DaoImplHibernate extends DaoImpl {

    private static final long serialVersionUID = 1L;

    public Session getCurrentSession() {
        return ServiceLocator.getCurrentSession();
    }

    @Override
    public CrudAdmin getCrudAdmin() {
        // SessionWrapper currentSessionWrapper =
        // ServiceLocator.getCurrentSessionWrapper();

        // if (currentSessionWrapper != null) {
        // return currentSessionWrapper.getInterceptor().getCrudAdmin();
        // } else {
        // return new CrudAdmin();
        // }
        return null;
    }

    @Override
    public boolean isManaged(Object entity) {
        return getCurrentSession().contains(entity);
    }

    @Override
    public abstract Class<?> getFeaturedClass();

    public KTMEntity get(Class<?> entityClass, Serializable id) {
        KTMEntity object = null;
        if (entityClass != null && id != null) {
            try {
                object = (KTMEntity) getCurrentSession().get(entityClass, id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return object;
    }

    @Override
    public Serializable createOrUpdate(KTMEntity object) throws CreateException {
        if (object == null) {
            throw new CreateException("Either given class or object was null");
        }

        Transaction transaction = null;

        try {
            transaction = getCurrentSession().beginTransaction();
            getCurrentSession().saveOrUpdate(object);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CreateException(e);
        }

        return object.getUniqueId();
    }

    @Override
    public KTMEntity update(KTMEntity object) throws UpdateException {
        if (object == null) {
            throw new UpdateException("Cannot update null object.");
        }
        if (get(object.getClass(), object.getUniqueId()) == null) {
            throw new UpdateException("Object to update not found.");
        }

        Transaction transaction = null;

        try {
            transaction = getCurrentSession().beginTransaction();
            getCurrentSession().update(object);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new UpdateException(e);
        }
        return object;
    }

    @Override
    public Serializable merge(KTMEntity object) throws StorageException {
        if (object == null) {
            throw new StorageException("Cannot merge null object");
        }

        Transaction transaction = null;

        try {
            transaction = getCurrentSession().beginTransaction();

            if (object.getUniqueId() == null || get(object.getClass(), object.getUniqueId()) == null) {
                return createOrUpdate(object);
            } else {
                try {
                    getCurrentSession().merge(object);
                } catch (Exception e) {
                    throw new StorageException(e);
                }
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new StorageException(e);
        }

        return object.getUniqueId();
    }

    public int delete(Class<?> entityClass, Serializable id) throws DeleteException {
        int result = 0;
        Transaction transaction = null;
        try {
            transaction = getCurrentSession().beginTransaction();

            if (get(entityClass, id) != null) {
                KTMEntity object = (KTMEntity) getCurrentSession().get(entityClass, id);
                getCurrentSession().delete(object);
                transaction.commit();
                result = 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DeleteException(e);
        }
        return result;
    }

    @Override
    public int delete(KTMEntity object) throws DeleteException {
        int result = 0;
        try {
            if (get(object.getClass(), object.getUniqueId()) != null) {
                getCurrentSession().delete(object);
                result = 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            throw new DeleteException(e);
        }
        return result;
    }

    public Collection<?> findAll(Class<?> entityClass) {
        List<KTMEntity> result = new ArrayList<KTMEntity>();
        try {
            List<?> objects = getCurrentSession().createQuery(queryStringAllFromClass(entityClass)).list();
            Iterator<?> it = objects.iterator();

            while (it != null && it.hasNext()) {
                Object object = it.next();

                if (object instanceof KTMEntity) {
                    result.add((KTMEntity) object);
                } else if (object instanceof Collection) {
                    Collection<?> subList = (Collection<?>) object;
                    for (Object subObject : subList) {
                        if (subObject instanceof KTMEntity) {
                            result.add((KTMEntity) subObject);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private String queryStringAllFromClass(Class<?> entityClass) {
        return "from " + entityClass.getName();
    }
}
