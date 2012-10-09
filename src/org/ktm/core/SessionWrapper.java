package org.ktm.core;

import java.io.Serializable;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

public class SessionWrapper implements Serializable {

    private static final long serialVersionUID = 1L;

    private Session           session;

    // private SessionStatisticsInterceptor interceptor;

    protected SessionWrapper() {
    }

    public SessionWrapper(SessionFactory sessionFactory) {
        // interceptor = new SessionStatisticsInterceptorImpl();
        // session = sessionFactory.openSession(interceptor);
        // session.setFlushMode(FlushMode.NEVER);

        session = sessionFactory.openSession();
    }

    public Session getSession() {
        return session;
    }

    // public SessionStatisticsInterceptor getInterceptor() {
    // return interceptor;
    // }
}
