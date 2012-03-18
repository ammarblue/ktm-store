package org.ktm.web.utils;

import java.net.URL;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Application Lifecycle Listener implementation class HibernateListener
 * 
 */
@WebListener
public class HibernateListener implements ServletContextListener {
    private Configuration      config;
    private SessionFactory     factory;
    private String             path     = "/hibernate.cfg.xml";
    private static Class<?>    clazz    = HibernateListener.class;

    public static final String KEY_NAME = clazz.getName();

    public void contextDestroyed(ServletContextEvent event) {
        //
    }

    public void contextInitialized(ServletContextEvent event) {

        try {
            URL url = HibernateListener.class.getResource(path);
            config = new Configuration().configure(url);
            factory = config.buildSessionFactory();

            // save the Hibernate session factory into serlvet context
            event.getServletContext().setAttribute(KEY_NAME, factory);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
