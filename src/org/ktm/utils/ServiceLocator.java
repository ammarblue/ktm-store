package org.ktm.utils;

import java.io.Serializable;
import javax.naming.NamingException;
import javax.naming.RefAddr;
import javax.naming.Reference;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.impl.SessionFactoryObjectFactory;

public class ServiceLocator implements Serializable {

    private static final long                  serialVersionUID          = 1L;

    private static Logger                      log                       = Logger.getLogger(ServiceLocator.class);

    private static ThreadLocal<SessionWrapper> threadLocalSessionWrapper = new ThreadLocal<SessionWrapper>();

    public static Session getCurrentSession() {
        Session session = null;
        SessionWrapper sessionWrapper = getCurrentSessionWrapper();

        if (sessionWrapper != null) {
            session = sessionWrapper.getSession();
        }
        if (session == null) {
            throw new IllegalStateException("Session must be available, but is unavailable.");
        } else if (!session.isOpen()) {
            throw new IllegalStateException("Session must be open, but is closed.");
        }

        return session;
    }

    public static SessionWrapper getCurrentSessionWrapper() {
        return threadLocalSessionWrapper.get();
    }

    public static void clearSessionWrapper() {
        threadLocalSessionWrapper.set(null);
    }

    public static void setSessionWrapper(SessionWrapper sessionWrapper) {
        threadLocalSessionWrapper.set(sessionWrapper);
    }

    protected static SessionWrapper getSessionWrapper(SessionFactory sessionFactory) {
        SessionWrapper sessionWrapper = getCurrentSessionWrapper();

        if (sessionWrapper == null) {
            sessionWrapper = new SessionWrapper(sessionFactory);
            setSessionWrapper(sessionWrapper);
        }

        return sessionWrapper;
    }

    protected static SessionTransactionWrapper getCurrentSessionTransactionWrapper() {

        if (threadLocalSessionWrapper.get() != null && (!(threadLocalSessionWrapper.get() instanceof SessionTransactionWrapper))) {
            throw new IllegalStateException("Current thread local session wrapper is not a SessionTransactionWrapper.");
        }

        return (SessionTransactionWrapper) threadLocalSessionWrapper.get();
    }

    protected static SessionTransactionWrapper getSessionTransactionWrapper(SessionFactory sessionFactory) {
        SessionTransactionWrapper sessionTransactionWrapper = getCurrentSessionTransactionWrapper();

        if (sessionTransactionWrapper == null) {
            sessionTransactionWrapper = new SessionTransactionWrapper(sessionFactory);
            setSessionWrapper(sessionTransactionWrapper);
        }

        return sessionTransactionWrapper;
    }

    protected static SessionContext getSessionContext(SessionFactory sessionFactory) {
        SessionTransactionWrapper sessionTransactionWrapper = getSessionTransactionWrapper(sessionFactory);
        return sessionTransactionWrapper.buildSessionContext();
    }

    protected static SessionFactory getSessionFactory(String hibernateConfigurationResource) {

        if (hibernateConfigurationResource == null || "".equals(hibernateConfigurationResource)) {
            throw new IllegalArgumentException("Cannot look-up session factory for a null or empty configuration file name.");
        }

        log.info("Configuring Hibernate, based on configuration resource: " + hibernateConfigurationResource);
        Configuration configuration = new Configuration().configure(hibernateConfigurationResource);

        log.info("Building session factory");
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Reference reference = null;

        try {
            reference = sessionFactory.getReference();
        } catch (NamingException ex) {
            // Ignored: probably hibernate changed something in the
            // implementation of JNDI registration.
            // Memory leak is to stay.
        }

        if (reference != null) {
            RefAddr refAddr = reference.get("uuid");
            Object content = refAddr != null ? refAddr.getContent() : null;
            String uuid = content != null ? content.toString() : null;

            if (uuid != null) {
                SessionFactoryObjectFactory.removeInstance(uuid, null, null);
            }
        }

        return sessionFactory;
    }
}
