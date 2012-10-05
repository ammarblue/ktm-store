package org.ktm.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ObjectsReferringTo implements Serializable {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("rawtypes")
    private final Map         referredObjects  = new HashMap();

    public ObjectsReferringTo() {
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void addAll(Object referredObject, Collection objectsReferringTo) {

        if (getReferredObjects().containsKey(referredObject)) {
            Collection currentObjectsReferringTo = (Collection) getReferredObjects().get(referredObject);
            currentObjectsReferringTo.addAll(objectsReferringTo); // No put-back
                                                                  // required.

        } else {
            getReferredObjects().put(referredObject, objectsReferringTo);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void add(Object referredObject, Object objectReferringTo) {

        if (getReferredObjects().containsKey(referredObject)) {
            Collection currentObjectsReferringTo = (Collection) getReferredObjects().get(referredObject);
            currentObjectsReferringTo.add(objectReferringTo); // No put-back
                                                              // required.

        } else {
            Set intialObjectsReferringTo = new HashSet();
            intialObjectsReferringTo.add(objectReferringTo);
            getReferredObjects().put(referredObject, intialObjectsReferringTo);
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Collection getObjectsReferringTo() {
        HashSet allObjectsReferringTo = new HashSet();
        Collection collection = getReferredObjects().values();

        if (collection != null) {
            allObjectsReferringTo.addAll(collection);
        }
        return allObjectsReferringTo;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Collection getObjectsReferringTo(Object referredObject) {
        HashSet objectsReferringToToReferredObject = new HashSet();
        Collection collection = (Collection) getReferredObjects().get(referredObject);

        if (collection != null) {
            objectsReferringToToReferredObject.addAll(collection);
        }
        return objectsReferringToToReferredObject;
    }

    public int size() {
        return getObjectsReferringTo().size();
    }

    public boolean isEmpty() {
        return getReferredObjects().isEmpty();
    }

    public void clear() {
        getObjectsReferringTo().clear();
        getReferredObjects().clear();
    }

    @SuppressWarnings("rawtypes")
    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Referring objects:\n");

        for (Iterator it = getReferredObjects().keySet().iterator(); it.hasNext();) {
            Object object = it.next();
            stringBuffer.append("The following referred object:\n" + object.toString() + "\n");
            stringBuffer.append("Is (previously was) referred to by the following objects:\n");
            Collection objectsReferringTo = (Collection) getReferredObjects().get(object);

            for (Iterator it1 = objectsReferringTo.iterator(); it1.hasNext();) {
                Object next = it1.next();
                stringBuffer.append("* " + next.toString() + "\n");
            }
        }
        return stringBuffer.toString();
    }

    @SuppressWarnings("rawtypes")
    public Map getReferredObjects() {
        return referredObjects;
    }
}