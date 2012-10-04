package org.ktm.dao;

import java.io.Serializable;
import java.util.Collection;
import org.ktm.domain.KTMEntity;
import org.ktm.exception.CreateException;
import org.ktm.exception.DeleteException;
import org.ktm.exception.StorageException;
import org.ktm.exception.UpdateException;

public interface Dao {

    public Class<?> getFeaturedClass();

    public static int     QUERY_FIRST_RESULT_DEFAULT           = 0;

    public static int     QUERY_MAX_RESULTS_DEFAULT            = Integer.MAX_VALUE;

    /**
     * Default value for optimistic lock mode.
     */
    public static boolean OPTIMISTIC_LOCKMODE_DEFAULT          = true;

    /**
     * Default value for converting persistence provider specific exceptions
     * into persistence provider neutral ones.
     */
    public static boolean EXCEPTION_CONVERSION_ENABLED_DEFAULT = true;

    /**
     * Returns the current persistence context's CrudAdmin. The current
     * persistence context may be unavailable (null).
     * 
     * @return the current persistence context's CrudAdmin; CrudAdmin will be
     *         empty if there is no current persistence context.
     */
    public CrudAdmin getCrudAdmin();

    /**
     * Evaluates if a certain entity is under control of the current persistence
     * context.
     * 
     * @param entity
     *            the entity to be evaluated.
     * @return true if entity is managed by the current persistence context;
     *         false otherwise.
     */
    public boolean isManaged(Object entity);

    /**
     * Get query's first result index.
     * 
     * @return query's first result index.
     */
    public int getFirstResult();

    /**
     * Enable paging by setting the query's first result index. If not set, the
     * index will be zero.
     * 
     * @param firstResult
     *            the query's first result index.
     */
    public void setFirstResult(int firstResult);

    /**
     * Get the query's max results index.
     * 
     * @return query's max result index.
     */
    public int getMaxResults();

    /**
     * Enable paging by setting the query's max results index. If not set, there
     * is no limit.
     * 
     * @param maxResults
     *            the query's max result index.
     */
    public void setMaxResults(int maxResults);

    /**
     * Return if current lock mode is optimistic.
     * 
     * @return true if current lock mode is optimistic; false otherwise.
     */
    public boolean getOptimisticLockMode();

    /**
     * Set current lock mode to optimistic or pessimistic.
     * 
     * @param optimisticLockMode
     *            true for optimistic, false for pessimistic.
     */
    public void setOptimisticLockMode(boolean optimisticLockMode);

    /**
     * Return whether or not paging is enabled. Paging is enabled if
     * getFirstResult() > QUERY_FIRST_RESULT_DEFAULT, and getMaxResults <
     * QUERY_MAX_RESULTS_DEFAULT.
     * 
     * @return true if paging enabled; false otherwise.
     */
    public boolean getPagingEnabled();

    /**
     * Return if exception conversion is enabled.
     * 
     * @return true if exception conversion is enabled; false otherwise.
     */
    public boolean getExceptionConversionEnabled();

    /**
     * Enable or disable exception conversion, i.e. conversion of persistence
     * provider specific exceptions into persistence provider neutral ones.
     * 
     * @param exceptionConversionEnabled
     *            whether or not to enable exception conversion.
     */
    public void setExceptionConversionEnabled(boolean exceptionConversionEnabled);

    public KTMEntity get(Serializable id);

    public Serializable create(KTMEntity object) throws CreateException;

    public KTMEntity update(KTMEntity object) throws UpdateException;

    public Serializable merge(KTMEntity object) throws StorageException;

    public int delete(Serializable id) throws DeleteException;

    public int delete(KTMEntity object) throws DeleteException;

    public Collection<?> findAll();

}
