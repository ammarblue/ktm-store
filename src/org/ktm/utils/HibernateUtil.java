package org.ktm.utils;

import javax.servlet.http.HttpSession;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class HibernateUtil {
    private static final String HIBERNAGE_SESSION_FACTORY = "HibernateUtil_SessionFactory";

    public static Session getSession(HttpSession httpSession) {
        SessionFactory sessionFactory = (SessionFactory) httpSession.getAttribute(HIBERNAGE_SESSION_FACTORY);
        if (sessionFactory == null) {
            try {
                sessionFactory = new Configuration().configure().buildSessionFactory();
            } catch (Throwable ex) {
                // Log exception!
                throw new ExceptionInInitializerError(ex);
            }
        }
        return sessionFactory.openSession();
    }
}