package org.ktm.dao;

import java.io.Serializable;
import java.util.Collection;
import org.ktm.domain.KTMEntity;
import org.ktm.exception.DeleteException;
import org.ktm.store.HibernateStorage;

public abstract class AbstractHibernateStorageDao extends HibernateStorage implements Dao {

    private static final long serialVersionUID   = 1L;

    private int               firstResult        = QUERY_FIRST_RESULT_DEFAULT;
    private int               maxResults         = QUERY_MAX_RESULTS_DEFAULT;
    private boolean           optimisticLockMode = OPTIMISTIC_LOCKMODE_DEFAULT;

    @Override
    public KTMEntity get(Serializable id) {
        return get(getFeaturedClass(), id);
    }

    @Override
    public int delete(Serializable id) throws DeleteException {
        return delete(getFeaturedClass(), id);
    }

    @Override
    public Collection<?> findAll() {
        return findAll(getFeaturedClass());
    }

    public int getFirstResult() {
        return firstResult;
    }

    public void setFirstResult(int firstResult) {
        this.firstResult = firstResult;
    }

    public int getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public boolean isOptimisticLockMode() {
        return optimisticLockMode;
    }

    public void setOptimisticLockMode(boolean optimisticLockMode) {
        this.optimisticLockMode = optimisticLockMode;
    }
}
