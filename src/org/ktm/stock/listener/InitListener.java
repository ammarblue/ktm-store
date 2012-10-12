package org.ktm.stock.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.ktm.core.KTMContext;
import org.ktm.dao.EDatabaseSystem;
import org.ktm.utils.Localizer;

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

        String databaseSystem = context.getInitParameter("database_system");
        KTMContext.databaseSystem = EDatabaseSystem.get(Integer.valueOf(databaseSystem));

        KTMContext.dateFormat = context.getInitParameter("date_format");

        KTMContext.databaseName = context.getInitParameter("database_name");
        Localizer.switchLocale(Localizer.eLanguage.THAI);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

}
