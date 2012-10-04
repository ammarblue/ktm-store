package org.ktm.dao.interceptor;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import org.apache.log4j.Logger;
import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.EntityMode;
import org.hibernate.Transaction;
import org.hibernate.type.Type;
import org.ktm.dao.CrudAdmin;

public class SessionStatisticsInterceptorImpl extends EmptyInterceptor implements SessionStatisticsInterceptor {

    private static final long serialVersionUID = 1L;

    private static Logger     log              = Logger.getLogger(SessionStatisticsInterceptorImpl.class);

    private final CrudAdmin   crudAdmin        = new CrudAdmin();

    @Override
    public CrudAdmin getCrudAdmin() {
        return crudAdmin;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {

        log.debug("OnDelete. Add entity to deleted collection: " + entity.getClass().getName());
        getCrudAdmin().addDeleted(entity);

        log.debug("Examining state. Add entities that are referring to this entity.");
        for (int i = 0; i < state.length; i++) {
            Object object = state[i];

            if (object != null) {
                if (isEntityCollection(object)) {
                    getCrudAdmin().addObjectsReferringToAll(entity, (Collection) object);
                } else if (isEntity(object)) {
                    getCrudAdmin().addObjectsReferringTo(entity, object);
                }
            }
        }
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {

        log.debug("OnFlushDirty. Add entity to updated collection.: " + entity.getClass().getName());
        getCrudAdmin().addUpdated(entity);

        log.debug("Examining state, and adding previously referred objects.");

        for (int i = 0; i < previousState.length; i++) {
            Object object = previousState[i];

            if (isEntityCollection(object)) {
                getCrudAdmin().addObjectsReferredByAll(entity, (Collection) object);
            } else if (isEntity(object)) {
                getCrudAdmin().addObjectsReferredBy(entity, object);
            }
        }

        return false;
    }

    @Override
    public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {

        log.debug("OnLoad. entity: " + entity + "\n id: " + id);
        getCrudAdmin().addRetrieved(entity);

        return false;
    }

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {

        log.debug("OnSave. Add entity to created collection: " + entity.getClass().getName());
        getCrudAdmin().addCreated(entity);

        return false;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public void postFlush(Iterator entities) {
        log.debug("PostFlush.");
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void preFlush(Iterator entities) {
        log.debug("PreFlush.");
    }

    @Override
    public Boolean isTransient(Object entity) {
        log.debug("IsTransient.");
        return null;
    }

    @Override
    public Object instantiate(String entityName, EntityMode entityMode, Serializable id) {
        log.debug("Instantiate.");
        return null;
    }

    @Override
    public int[] findDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {

        log.debug("FindDirty");
        return null;
    }

    @Override
    public String getEntityName(Object object) {
        log.debug("GetEntityName");
        return null;
    }

    @Override
    public Object getEntity(String entityName, Serializable id) {
        log.debug("GetEntity");
        return null;
    }

    @Override
    public void afterTransactionBegin(Transaction tx) {
        log.debug("AfterTransactionBegin");
    }

    @Override
    public void afterTransactionCompletion(Transaction tx) {
        log.debug("AfterTransactionCompletion: clearing the created, updated, and deleted collection.");
    }

    @Override
    public void beforeTransactionCompletion(Transaction tx) {
        log.debug("beforeTransactionCompletion");
        if (tx == null) {
            log.debug("Transaction is not active.");
        } else if (tx.isActive()) {
            log.debug("Transaction is active.");
        } else {
            log.debug("Transaction is not active.");
        }
    }

    @Override
    public String onPrepareStatement(String sql) {
        log.debug("OnPrepareStatement.");
        return sql;
    }

    @Override
    public void onCollectionRemove(Object collection, Serializable key) throws CallbackException {
        log.debug("OnCollectionRemove.");
    }

    @Override
    public void onCollectionRecreate(Object collection, Serializable key) throws CallbackException {
        log.debug("OnCollectionRecreate.");
    }

    @Override
    public void onCollectionUpdate(Object collection, Serializable key) throws CallbackException {
        log.debug("OnCollectionUpdate.");
    }

    protected boolean isEntity(Object object) {
        return getCrudAdmin().contains(object);
    }

    @SuppressWarnings("rawtypes")
    protected boolean isEntityCollection(Object object) {
        boolean isEntityCollection = false;

        if (object instanceof Collection) {
            Collection collCandidate = (Collection) object;

            isEntityCollection = collCandidate.size() > 0 && isEntity(collCandidate.iterator().next());
        }

        return isEntityCollection;
    }
}
