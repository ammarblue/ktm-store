package org.ktm.store;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.ktm.core.KTMContext;
import org.ktm.domain.KTMEntity;
import org.ktm.exception.CreateException;
import org.ktm.exception.DeleteException;
import org.ktm.exception.StorageException;
import org.ktm.exception.UpdateException;
import org.ktm.utils.HibernateUtil;

public class HibernateStorage extends Storage {

    private static final long serialVersionUID = 1L;

    @Override
    public KTMEntity get(Class<?> entityClass, Serializable id) {
        KTMEntity object = null;
        if (entityClass != null && id != null) {
            Session session = HibernateUtil.getSession(KTMContext.getInstance().getSession());
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                object = (KTMEntity) session.get(entityClass, id);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                session.close();
            }
        }
        return object;
    }

    @Override
    public Serializable create(KTMEntity object) throws CreateException {
        if (object == null) {
            throw new CreateException("Either given class or object was null");
        }

        Session session = HibernateUtil.getSession(KTMContext.getInstance().getSession());
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(object);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new CreateException(e);
        } finally {
            session.close();
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

        Session session = HibernateUtil.getSession(KTMContext.getInstance().getSession());
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(object);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new UpdateException(e);
        } finally {
            session.close();
        }
        return object;
    }

    @Override
    public Serializable merge(KTMEntity object) throws StorageException {
        if (object == null) {
            throw new StorageException("Cannot merge null object");
        }
        if (object.getUniqueId() == null || get(object.getClass(), object.getUniqueId()) == null) {
            return create(object);
        } else {
            Session session = HibernateUtil.getSession(KTMContext.getInstance().getSession());
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.merge(object);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new StorageException(e);
            } finally {
                session.close();
            }
        }
        return object.getUniqueId();
    }

    @Override
    public int delete(Class<?> entityClass, Serializable id) throws DeleteException {
        int result = 0;
        Session session = HibernateUtil.getSession(KTMContext.getInstance().getSession());
        Transaction transaction = null;
        try {
            if (get(entityClass, id) != null) {
                transaction = session.beginTransaction();
                KTMEntity object = (KTMEntity) session.get(entityClass, id);
                session.delete(object);
                result = 1;
                transaction.commit();
            } else {
                return 0;
            }
        } catch (Exception e) {
            transaction.rollback();
            throw new DeleteException(e);
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public int delete(KTMEntity object) throws DeleteException {
        int result = 0;
        Session session = HibernateUtil.getSession(KTMContext.getInstance().getSession());
        Transaction transaction = null;
        try {
            if (get(object.getClass(), object.getUniqueId()) != null) {
                transaction = session.beginTransaction();
                session.delete(object);
                result = 1;
                transaction.commit();
            } else {
                return 0;
            }
        } catch (Exception e) {
            transaction.rollback();
            throw new DeleteException(e);
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public Collection<?> findAll(Class<?> entityClass) {
        List<KTMEntity> result = new ArrayList<KTMEntity>();
        Session session = HibernateUtil.getSession(KTMContext.getInstance().getSession());
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            List<?> objects = session.createQuery(queryStringAllFromClass(entityClass)).list();
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
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    private String queryStringAllFromClass(Class<?> entityClass) {
        return "from " + entityClass.getName();
    }

}
