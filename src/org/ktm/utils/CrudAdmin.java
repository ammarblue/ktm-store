package org.ktm.utils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CrudAdmin implements Serializable {

    private static final long        serialVersionUID       = 1L;

    /**
     * Defines to which nesting level toString() should print out contained
     * pojos.
     */
    private static final Integer     TOSTRING_NESTING_LEVEL = new Integer(1);

    /**
     * Created collection.
     */
    @SuppressWarnings("rawtypes")
    private final Set                createdCollection      = new HashSet();

    /**
     * Retrieved collection.
     */
    @SuppressWarnings("rawtypes")
    private final Set                retrievedCollection    = new HashSet();

    /**
     * Updated collection.
     */
    @SuppressWarnings("rawtypes")
    private final Set                updatedCollection      = new HashSet();

    /**
     * Deleted collection.
     */
    @SuppressWarnings("rawtypes")
    private final Set                deletedCollection      = new HashSet();

    /**
     * Objects referring to a certain other.
     */
    private final ObjectsReferringTo objectsReferringTo     = new ObjectsReferringTo();

    /**
     * Objects referred by a certain other.
     */
    @SuppressWarnings("rawtypes")
    private final Map                objectsReferredBy      = new HashMap();

    /**
     * Get created collection.
     * 
     * @return the created collection.
     */
    @SuppressWarnings("rawtypes")
    public Set getCreatedCollection() {
        return createdCollection;
    }

    /**
     * Add created entity to CrudAdmin.
     * 
     * @param entity
     *            the created entity
     */
    @SuppressWarnings("unchecked")
    public void addCreated(Object entity) {
        getCreatedCollection().add(entity);

        if (getDeletedCollection().contains(entity)) {
            getDeletedCollection().remove(entity);
        } else if (getRetrievedCollection().contains(entity)) {
            getRetrievedCollection().remove(entity);
        } else if (getUpdatedCollection().contains(entity)) {
            getUpdatedCollection().remove(entity);
        }
    }

    /**
     * Get retrieved collection.
     * 
     * @return the retrieved collection.
     */
    @SuppressWarnings("rawtypes")
    public Set getRetrievedCollection() {
        return retrievedCollection;
    }

    /**
     * Add retrieved entity to CrudAdmin.
     * 
     * @param entity
     *            the retrieved entity
     */
    @SuppressWarnings("unchecked")
    public void addRetrieved(Object entity) {
        if (!getCUDCollection().contains(entity)) {
            getRetrievedCollection().add(entity);
        }
    }

    /**
     * Get updated collection.
     * 
     * @return the updated collection.
     */
    @SuppressWarnings("rawtypes")
    public Set getUpdatedCollection() {
        return updatedCollection;
    }

    /**
     * Add updated entity to CrudAdmin.
     * 
     * @param entity
     *            the updated entity
     */
    @SuppressWarnings("unchecked")
    public void addUpdated(Object entity) {
        getUpdatedCollection().add(entity);
        if (getCreatedCollection().contains(entity)) {
            getCreatedCollection().remove(entity);
        } else if (getRetrievedCollection().contains(entity)) {
            getRetrievedCollection().remove(entity);
        } else if (getDeletedCollection().contains(entity)) {
            getDeletedCollection().remove(entity);
        }
    }

    /**
     * Get deleted collection.
     * 
     * @return the deleted collection.
     */
    @SuppressWarnings("rawtypes")
    public Set getDeletedCollection() {
        return deletedCollection;
    }

    /**
     * Add deleted entity to CrudAdmin.
     * 
     * @param entity
     *            the deleted entity
     */
    @SuppressWarnings("unchecked")
    public void addDeleted(Object entity) {
        getDeletedCollection().add(entity);
        if (getCreatedCollection().contains(entity)) {
            getCreatedCollection().remove(entity);
        } else if (getRetrievedCollection().contains(entity)) {
            getRetrievedCollection().remove(entity);
        } else if (getUpdatedCollection().contains(entity)) {
            getUpdatedCollection().remove(entity);
        }
    }

    /**
     * Get objects referring to another.
     * 
     * @return objects referring to another.
     */
    public ObjectsReferringTo getObjectsReferringTo() {
        return objectsReferringTo;
    }

    /**
     * Add object referring to entity to CrudAdmin.
     * 
     * @param entity
     *            the entity being referred to
     * @param referringObject
     *            the object referring to entity
     */
    public void addObjectsReferringTo(Object entity, Object referringObject) {
        getObjectsReferringTo().add(entity, referringObject);
    }

    /**
     * Add all objects referring to entity to CrudAdmin.
     * 
     * @param entity
     *            the entity being referred to
     * @param referringObjects
     *            the objects referring to entity
     */
    @SuppressWarnings("rawtypes")
    public void addObjectsReferringToAll(Object entity, Collection referringObjects) {
        getObjectsReferringTo().addAll(entity, referringObjects);
    }

    /**
     * Get objects referred by another.
     * 
     * @return objects referred by another.
     */
    @SuppressWarnings("rawtypes")
    public Map getObjectsReferredBy() {
        return objectsReferredBy;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private Set getObjectsReferredBy(Object entity) {
        Set currentReferredObjects = (Set) getObjectsReferredBy().get(entity);
        if (currentReferredObjects == null) {
            currentReferredObjects = new HashSet();
            getObjectsReferredBy().put(entity, currentReferredObjects);
        }

        return currentReferredObjects;
    }

    /**
     * Add object referred by entity to CrudAdmin.
     * 
     * @param entity
     *            the referencing entity
     * @param referredObject
     *            the object referred by entity
     */
    @SuppressWarnings("unchecked")
    public void addObjectsReferredBy(Object entity, Object referredObject) {
        getObjectsReferredBy(entity).add(referredObject);
    }

    /**
     * Add objects referred by entity to CrudAdmin.
     * 
     * @param entity
     *            the referencing entity
     * @param referredObjects
     *            the objects referred by entity
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void addObjectsReferredByAll(Object entity, Collection referredObjects) {
        getObjectsReferredBy(entity).addAll(referredObjects);
    }

    /**
     * Clear the CRUD administration.
     */
    public synchronized void clear() {
        getCreatedCollection().clear();
        getRetrievedCollection().clear();
        getUpdatedCollection().clear();
        getDeletedCollection().clear();
        getObjectsReferringTo().clear();
        getObjectsReferredBy().clear();
    }

    /**
     * Convenience method in order to return the created, updated, deleted
     * collection combined into one collection.
     * 
     * @return the created, updated, deleted collection combined into one
     *         collection.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public synchronized Set getCUDCollection() {
        Set cudCollection = new HashSet();

        cudCollection.addAll(getCreatedCollection());
        cudCollection.addAll(getUpdatedCollection());
        cudCollection.addAll(getDeletedCollection());

        return cudCollection;
    }

    /**
     * Examine if a certain entity has been created / retrieved / updated /
     * deleted so far.
     * 
     * @return true if entity has been created / retrieved / updated / deleted
     *         so far; false otherwise.
     */
    public synchronized boolean contains(Object entity) {
        return getRetrievedCollection().contains(entity) || getCUDCollection().contains(entity);
    }

    /**
     * Get the amount of created / retrieved / updated / deleted entities.
     */
    public synchronized int cudSize() {
        return getCreatedCollection().size() + getDeletedCollection().size() + getUpdatedCollection().size();
    }

    /**
     * Get a string representation of the CRUD administration.
     * 
     * @return a string representation of the CRUD administration.
     */
    @Override
    public synchronized String toString() {
        StringBuffer stringBuffer = new StringBuffer();

        getAsString(stringBuffer, "Created Collection", getCreatedCollection());
        getAsString(stringBuffer, "Retrieved Collection", getRetrievedCollection());
        getAsString(stringBuffer, "Updated Collection", getUpdatedCollection());
        getAsString(stringBuffer, "Deleted Collection", getDeletedCollection());
        getAsString(stringBuffer, "Referring Objects", getObjectsReferringTo().getReferredObjects());
        getAsString(stringBuffer, "Referred Objects", getObjectsReferredBy());

        return stringBuffer.toString();
    }

    @SuppressWarnings("rawtypes")
    private void getAsString(StringBuffer stringBuffer, String collectionType, Collection pojoCollection) {
        stringBuffer.append(collectionType + " with size:" + pojoCollection.size() + " contains: {\n");
        for (Iterator pojos = pojoCollection.iterator(); pojos.hasNext();) {
            stringBuffer.append(getAsString(pojos.next()) + "\n");
        }
        stringBuffer.append("}\n");
    }

    @SuppressWarnings("rawtypes")
    private void getAsString(StringBuffer stringBuffer, String mapType, Map pojoMap) {
        stringBuffer.append(mapType + " with size:" + pojoMap.size() + " contains: {\n");
        for (Iterator it = pojoMap.keySet().iterator(); it.hasNext();) {
            Object object = it.next();
            stringBuffer.append("The following referred object:\n" + getAsString(object) + "\n");
            stringBuffer.append("Is (previously was) referred to by the following objects:\n");
            Collection mappedObjects = (Collection) pojoMap.get(object);

            for (Iterator it1 = mappedObjects.iterator(); it1.hasNext();) {
                stringBuffer.append("* " + getAsString(it1.next()) + "\n");
            }
        }
        stringBuffer.append("}\n");
    }

    @SuppressWarnings("rawtypes")
    private String getAsString(Object pojo) {
        try {
            Class[] formalParameters = new Class[] { Integer.TYPE };
            Object[] actualParameters = new Object[] { TOSTRING_NESTING_LEVEL };

            Method method = pojo.getClass().getDeclaredMethod("toString", formalParameters);

            return (String) method.invoke(pojo, actualParameters);
        } catch (Exception ex) {
            // Should never happen, since OptimalJ pojo always implements
            // toString(int nestingLevel)
            return "pojo.toString(int nestingLevel) not found; defaulting to pojo.toString(): " + pojo.toString();
        }
    }
}