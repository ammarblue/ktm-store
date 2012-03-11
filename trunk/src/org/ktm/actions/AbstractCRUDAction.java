package org.ktm.actions;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.log4j.Logger;
import org.ktm.web.form.FrmDomain;
import org.ktm.web.manager.FormManager;

public abstract class AbstractCRUDAction extends KTMActionSupport {

    private static final long     serialVersionUID = -3092632134847280150L;
    private Logger                log              = Logger.getLogger(AbstractCRUDAction.class);

    private Collection<?> availableItems;
    private Set<FrmDomain>        toDelete         = new HashSet<FrmDomain>();
    private Set<FrmDomain>        toAdd            = new HashSet<FrmDomain>();

    protected abstract FormManager getManager();

    public Collection<?> getAvailableItems() {
        return availableItems;
    }
    
    public void setAvailableItems(Collection<?> subList) {
        availableItems = subList;
    }

    public Set<FrmDomain> getToDelete() {
        return toDelete;
    }

    public Set<FrmDomain> getToAdd() {
        return toAdd;
    }

    public void list(KTMAction action) throws Exception {
        this.availableItems = (Collection<FrmDomain>) getManager().findAll(action);
        if (log.isDebugEnabled()) {
            log.debug("AbstractCRUDAction - [list]: " + (availableItems != null ? "" + availableItems.size() : "no") + " items found");
        }
    }

    public void add(KTMAction action) throws Exception {
        if (toAdd != null) {
            int count = 0;
            for (FrmDomain obj : getToAdd()) {
                count = count + getManager().addOrUpdate(action, obj);
            }
            if (log.isDebugEnabled()) {
                log.debug("AbstractCRUDAction - [delete]: " + count + " items deleted.");
            }
        }
    }

    public void delete(KTMAction action) throws Exception {
        if (toDelete != null) {
            int count = 0;
            for (FrmDomain obj : getToDelete()) {
                count = count + getManager().delete(action, obj);
            }
            if (log.isDebugEnabled()) {
                log.debug("AbstractCRUDAction - [delete]: " + count + " items deleted.");
            }
        }
    }

    /**
     * Utility method for fetching already persistent object from storage for
     * usage in params-prepare-params cycle.
     * 
     * @param tryId
     *            The id to try to get persistent object for
     * @param tryObject
     *            The object, induced by first params invocation, possibly
     *            containing id to try to get persistent object for
     * @return The persistent object, if found. <tt>null</tt> otherwise.
     */
    protected FrmDomain fetch(KTMAction action, Serializable tryId, FrmDomain tryObject) {
        FrmDomain result = null;
        if (tryId != null) {
            result = getManager().get(action, tryId);
        } else if (tryObject != null) {
            result = getManager().get(action, tryObject.getId());
        }
        return result;
    }
}
