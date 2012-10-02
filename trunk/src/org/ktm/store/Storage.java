package org.ktm.store;

import java.io.Serializable;
import java.util.Collection;
import org.ktm.domain.KTMEntity;
import org.ktm.exception.CreateException;
import org.ktm.exception.DeleteException;
import org.ktm.exception.StorageException;
import org.ktm.exception.UpdateException;

public abstract class Storage implements Serializable {

    private static final long serialVersionUID                     = 1L;

    public static int         QUERY_FIRST_RESULT_DEFAULT           = 0;
    public static int         QUERY_MAX_RESULTS_DEFAULT            = Integer.MAX_VALUE;
    public static boolean     OPTIMISTIC_LOCKMODE_DEFAULT          = true;
    public static boolean     EXCEPTION_CONVERSION_ENABLED_DEFAULT = true;

    public abstract KTMEntity get(Class<?> entityClass, Serializable id);

    public abstract Serializable create(KTMEntity object) throws CreateException;

    public abstract KTMEntity update(KTMEntity object) throws UpdateException;

    public abstract Serializable merge(KTMEntity object) throws StorageException;

    public abstract int delete(Class<?> entityClass, Serializable id) throws DeleteException;

    public abstract int delete(KTMEntity object) throws DeleteException;

    public abstract Collection<?> findAll(Class<?> entityClass);
}
