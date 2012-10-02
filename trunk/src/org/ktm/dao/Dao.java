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

    public KTMEntity get(Serializable id);

    public Serializable create(KTMEntity object) throws CreateException;

    public KTMEntity update(KTMEntity object) throws UpdateException;

    public Serializable merge(KTMEntity object) throws StorageException;

    public int delete(Serializable id) throws DeleteException;

    public int delete(KTMEntity object) throws DeleteException;

    public Collection<?> findAll();

}
