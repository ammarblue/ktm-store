package org.ktm.dao;

import java.io.Serializable;
import java.util.Collection;
import org.ktm.domain.KTMEntity;
import org.ktm.exception.DeleteException;

public abstract class AbstractHibernateStorageDao extends DaoImplHibernate implements Dao {

    private static final long serialVersionUID = 1L;

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
}
