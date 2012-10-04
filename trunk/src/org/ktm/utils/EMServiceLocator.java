package org.ktm.utils;

import org.hibernate.SessionFactory;
import org.ktm.core.SessionContext;
import org.ktm.core.SessionWrapper;

public class EMServiceLocator extends ServiceLocator {

    private static final long     serialVersionUID      = 1L;

    private static SessionFactory sessionFactory;

    private static int[]          newSessionFactoryLock = new int[0];

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            SessionFactory newSessionFactory = getSessionFactory("/hibernate.cfg.xml");

            synchronized (newSessionFactoryLock) {
                if (sessionFactory == null) {
                    sessionFactory = newSessionFactory;
                    newSessionFactory = null;
                }
            }

            if (newSessionFactory != null) {
                newSessionFactory.close();
            }
        }

        return sessionFactory;
    }

    public static SessionWrapper getSessionWrapper() {
        return ServiceLocator.getSessionWrapper(getSessionFactory());
    }

    public static SessionContext getSessionContext() {
        return ServiceLocator.getSessionContext(getSessionFactory());
    }
}
