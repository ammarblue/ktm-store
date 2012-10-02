package org.ktm.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.hibernate.classic.Session;
import org.ktm.core.KTMContext;
import org.ktm.utils.HibernateUtil;

@WebListener
public class SessionInitListener implements HttpSessionListener {

    public SessionInitListener() {

    }

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession httpSession = event.getSession();
        Session session = HibernateUtil.getSession(httpSession);
        session.close();

        KTMContext.setSession(httpSession);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {

    }

}
