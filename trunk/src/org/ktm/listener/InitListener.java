package org.ktm.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.ktm.core.KTMContext;
import org.ktm.tags.MessageManager;

//import java.util.logging.Logger;

@WebListener
public class InitListener implements ServletContextListener {

    public InitListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        KTMContext ktmContext = new KTMContext();

        context.setAttribute(KTMContext.APPCONTEXT, ktmContext);

        ktmContext.setJspHeader(context.getInitParameter("JspHeader"));
        ktmContext.setJspFooter(context.getInitParameter("JspFooter"));

        MessageManager.switchLocale(MessageManager.eLanguage.THAI);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

}
