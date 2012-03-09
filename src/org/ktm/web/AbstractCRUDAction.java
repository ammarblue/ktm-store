/*
 * $Id: AbstractCRUDAction.java 476710 2006-11-19 05:05:14Z mrdon $
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.ktm.web;

import java.io.Serializable;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.ktm.dao.Dao;
import org.ktm.domain.KTMEntity;
import org.ktm.domain.KTMEntityID;

public abstract class AbstractCRUDAction extends KTMActionSupport {

    private static final long serialVersionUID = -3092632134847280150L;
    private Logger log = Logger.getLogger(AbstractCRUDAction.class);

    private Collection<KTMEntity> availableItems;
    private KTMEntityID[] toDelete;

    protected abstract Dao getDao();

    @SuppressWarnings("rawtypes")
    public Collection getAvailableItems() {
        return availableItems;
    }

    public KTMEntityID[] getToDelete() {
        return toDelete;
    }

    public void setToDelete(KTMEntityID[] toDelete) {
        this.toDelete = toDelete;
    }

    @SuppressWarnings("unchecked")
    public String list() throws Exception {
        this.availableItems = (Collection<KTMEntity>) getDao().findAll();
        if (log.isDebugEnabled()) {
            log.debug("AbstractCRUDAction - [list]: " + (availableItems != null ? "" + availableItems.size() : "no") + " items found");
        }
        return execute();
    }

    public String delete() throws Exception {
        if (toDelete != null) {
            int count = 0;
            for (int i = 0, j = toDelete.length; i < j; i++) {
                count = count + getDao().delete(toDelete[i]);
            }
            if (log.isDebugEnabled()) {
                log.debug("AbstractCRUDAction - [delete]: " + count + " items deleted.");
            }
        }
        return SUCCESS;
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
    protected KTMEntity fetch(Serializable tryId, KTMEntity tryObject) {
        KTMEntity result = null;
        if (tryId != null) {
            result = getDao().get(tryId);
        } else if (tryObject != null) {
            result = getDao().get(tryObject.getUniqueId());
        }
        return result;
    }
}