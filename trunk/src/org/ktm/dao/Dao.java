package org.ktm.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.ktm.domain.KTMEntity;
import org.ktm.exception.CreateException;
import org.ktm.exception.DeleteException;
import org.ktm.exception.StorageException;
import org.ktm.exception.UpdateException;

public interface Dao {

    public static int     QUERY_FIRST_RESULT_DEFAULT           = 0;
    public static int     QUERY_MAX_RESULTS_DEFAULT            = Integer.MAX_VALUE;
    public static boolean OPTIMISTIC_LOCKMODE_DEFAULT          = true;
    public static boolean EXCEPTION_CONVERSION_ENABLED_DEFAULT = true;

    public Class<?> getFeaturedClass();

    public KTMEntity get(Serializable id);

    public Serializable create(KTMEntity object) throws CreateException;

    public KTMEntity update(KTMEntity object) throws UpdateException;

    public Serializable merge(KTMEntity object) throws StorageException;

    public int delete(Serializable id) throws DeleteException;

    public int delete(KTMEntity object) throws DeleteException;

    public Collection<?> findAll();

    public List<?> getSubList(List<?> cols, int form, int to);

    public List<?> findNotById(List<?> cols, int id, int from, int to);

    public List<?> findGreaterAsId(List<?> list, int id, int from, int to);

    public List<?> findLesserAsId(List<?> list, int id, int from, int to);

}
