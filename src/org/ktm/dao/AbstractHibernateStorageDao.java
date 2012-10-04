package org.ktm.dao;

import java.io.Serializable;
import java.util.Collection;
import org.ktm.domain.KTMEntity;
import org.ktm.exception.DeleteException;

public abstract class AbstractHibernateStorageDao extends DaoImplHibernate implements Dao {

    private static final long serialVersionUID   = 1L;

    private int               firstResult        = QUERY_FIRST_RESULT_DEFAULT;
    private int               maxResults         = QUERY_MAX_RESULTS_DEFAULT;
    private boolean           optimisticLockMode = OPTIMISTIC_LOCKMODE_DEFAULT;

    public KTMEntity get(Serializable id) {
        return get(getFeaturedClass(), id);
    }

    public int delete(Serializable id) throws DeleteException {
        return delete(getFeaturedClass(), id);
    }

    public Collection<?> findAll() {
        return findAll(getFeaturedClass());
    }

    @Override
    public int getFirstResult() {
        return firstResult;
    }

    @Override
    public void setFirstResult(int firstResult) {
        this.firstResult = firstResult;
    }

    @Override
    public int getMaxResults() {
        return maxResults;
    }

    @Override
    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public boolean isOptimisticLockMode() {
        return optimisticLockMode;
    }

    @Override
    public void setOptimisticLockMode(boolean optimisticLockMode) {
        this.optimisticLockMode = optimisticLockMode;
    }
}
