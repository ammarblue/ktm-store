package org.ktm.dao;

import java.io.Serializable;

public abstract class DaoImpl implements Dao, Serializable {

    /**
     * Serial version Id.
     */
    private static final long serialVersionUID           = 1L;

    /**
     * Query's first result index.
     */
    private int               firstResult                = QUERY_FIRST_RESULT_DEFAULT;

    /**
     * Query's max results index.
     */
    private int               maxResults                 = QUERY_MAX_RESULTS_DEFAULT;

    /**
     * Optimistic lock mode.
     */
    private boolean           optimisticLockMode         = OPTIMISTIC_LOCKMODE_DEFAULT;

    /**
     * Exception conversion enabled.
     */
    private boolean           exceptionConversionEnabled = EXCEPTION_CONVERSION_ENABLED_DEFAULT;

    @Override
    public abstract CrudAdmin getCrudAdmin();

    @Override
    public abstract boolean isManaged(Object entity);

    @Override
    public int getFirstResult() {
        return firstResult;
    }

    @Override
    public void setFirstResult(int firstResult) {
        if (firstResult < 0) {
            throw new IllegalArgumentException("Cannot set firstResult < 0: " + firstResult);
        }
        this.firstResult = firstResult;
    }

    @Override
    public int getMaxResults() {
        return maxResults;
    }

    @Override
    public void setMaxResults(int maxResults) {
        if (maxResults < 1) {
            throw new IllegalArgumentException("Cannot set maxResults < 1: " + maxResults);
        }
        this.maxResults = maxResults;
    }

    @Override
    public boolean getOptimisticLockMode() {
        return optimisticLockMode;
    }

    @Override
    public void setOptimisticLockMode(boolean optimisticLockMode) {
        this.optimisticLockMode = optimisticLockMode;
    }

    @Override
    public boolean getExceptionConversionEnabled() {
        return exceptionConversionEnabled;
    }

    @Override
    public void setExceptionConversionEnabled(boolean exceptionConversionEnabled) {
        this.exceptionConversionEnabled = exceptionConversionEnabled;
    }

    @Override
    public boolean getPagingEnabled() {
        return ((firstResult != QUERY_FIRST_RESULT_DEFAULT) || (maxResults != QUERY_MAX_RESULTS_DEFAULT));
    }
}