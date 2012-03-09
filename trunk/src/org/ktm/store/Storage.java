package org.ktm.store;

import java.io.Serializable;
import java.util.Collection;

import org.hibernate.Query;
import org.ktm.domain.KTMEntity;
import org.ktm.exception.CreateException;
import org.ktm.exception.DeleteException;
import org.ktm.exception.StorageException;
import org.ktm.exception.UpdateException;

public interface Storage extends Serializable {
	
    KTMEntity get( Class<?> entityClass, Serializable id );

    Serializable create ( KTMEntity object ) throws CreateException;

    KTMEntity update ( KTMEntity object ) throws UpdateException;

    Serializable merge ( KTMEntity object ) throws StorageException;

    int delete( Class<?> entityClass, Serializable id ) throws DeleteException;

    int delete( KTMEntity object ) throws DeleteException;

    Collection<?> findAll( Class<?> entityClass );
    
    Query getQuery(String queryString);
}
