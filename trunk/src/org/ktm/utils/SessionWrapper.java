package org.ktm.utils;

import java.io.Serializable;
import org.hibernate.FlushMode;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.ktm.dao.interceptor.SessionStatisticsInterceptor;
import org.ktm.dao.interceptor.SessionStatisticsInterceptorImpl;

public class SessionWrapper implements Serializable {

    private static final long            serialVersionUID = 1L;

    private Session                      session;

    private SessionStatisticsInterceptor interceptor;

    protected SessionWrapper() {
    }

    @SuppressWarnings("deprecation")
    public SessionWrapper(SessionFactory sessionFactory) {
        interceptor = new SessionStatisticsInterceptorImpl();
        session = sessionFactory.openSession(interceptor);
        session.setFlushMode(FlushMode.NEVER);
    }

    public Session getSession() {
        return session;
    }

    public SessionStatisticsInterceptor getInterceptor() {
        return interceptor;
    }
}
