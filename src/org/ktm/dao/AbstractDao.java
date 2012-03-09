package org.ktm.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.ktm.store.HibernateStorage;
import org.ktm.store.MemoryStorage;
import org.ktm.store.Storage;
import org.ktm.web.KTMAction;
import org.ktm.domain.KTMEntity;
import org.ktm.exception.CreateException;
import org.ktm.exception.DeleteException;
import org.ktm.exception.StorageException;
import org.ktm.exception.UpdateException;

public abstract class AbstractDao implements Serializable, Dao {

	private static final long serialVersionUID = -3486401762016380671L;

    private int firstResult = QUERY_FIRST_RESULT_DEFAULT;
    private int maxResults = QUERY_MAX_RESULTS_DEFAULT;
    private boolean optimisticLockMode = OPTIMISTIC_LOCKMODE_DEFAULT;
    
	private Storage storage = null;

	public AbstractDao(KTMAction curdAction, PersistanceType type) {
		if (type == PersistanceType.HIBERNATE) {
			storage = new HibernateStorage(curdAction);
		} 
		else if (type == PersistanceType.MEMORY) {
			storage = new MemoryStorage();
		}
	}

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

	public KTMEntity get(Serializable id) {
		return getStorage().get(getFeaturedClass(), id);
	}

	public Serializable create(KTMEntity object) throws CreateException {
		return getStorage().create(object);
	}

	public KTMEntity update(KTMEntity object) throws UpdateException {
		return getStorage().update(object);
	}

	public Serializable merge(KTMEntity object) throws StorageException {
		return getStorage().merge(object);
	}

	public int delete(Serializable id) throws DeleteException {
		return getStorage().delete(getFeaturedClass(), id);
	}

	public int delete(KTMEntity object) throws DeleteException {
		return getStorage().delete(object);
	}

	public Collection<?> findAll() {
		return getStorage().findAll(getFeaturedClass());
	}

	public List<?> getSubList(List<?> cols, int form, int to) {
		return cols.subList(form, to);
	}

	public List<?> findNotById(List<?> cols, int id, int from, int to) {
		List<KTMEntity> sResult = new ArrayList<KTMEntity>();

		for (Object obj : cols) {
			if (obj instanceof KTMEntity && ((KTMEntity)obj).getUniqueId() != id)
				sResult.add((KTMEntity)obj);
		}

		return sResult.subList(from, to);
	}

	public List<?> findGreaterAsId(List<?> list, int id, int from, int to) {
		List<KTMEntity> sResult = new ArrayList<KTMEntity>();

		for (Object obj : list) {
			if (obj instanceof KTMEntity && ((KTMEntity)obj).getUniqueId() > id)
				sResult.add((KTMEntity)obj);
		}

		return sResult.subList(from, to);
	}

	public List<?> findLesserAsId(List<?> list, int id, int from, int to) {
		List<KTMEntity> sResult = new ArrayList<KTMEntity>();

		for (Object obj : list) {
			if (obj instanceof KTMEntity && ((KTMEntity)obj).getUniqueId() < id)
				sResult.add((KTMEntity)obj);
		}

		return sResult.subList(from, to);
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
