package org.ktm.stock;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.ktm.servlet.AbstractServlet;
import org.ktm.servlet.ActionForward;
import org.ktm.tags.auth.AuthenticatorFactory;
import org.ktm.utils.Globals;
import org.ktm.web.bean.FormBean;

@WebServlet("/index")
public class Index extends AbstractServlet {
    private static final long serialVersionUID = 1L;

    private final Logger      log              = Logger.getLogger(Index.class);

    private String            redirectName;

    @Override
    public String getBeanClass() {
        return "org.ktm.stock.bean.MainBean";
    }

    public String getRedirectName() {
        return redirectName;
    }

    @Override
    protected ActionForward processRequest(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        redirectName = request.getParameter("page");
        ActionForward action = null;

        String pageType = request.getParameter("t");
        if (pageType != null && redirectName != null) {
            if (pageType.equals("t")) {
                log.info("Go to page: " + redirectName);
                action = ActionForward.getAction(this, request, redirectName);
            }
        }
        if (redirectName == null) {
            if (AuthenticatorFactory.isUserLoggedIn(request)) {
                ServletContext context = request.getServletContext();
                String mainPage = context.getInitParameter(Globals.MAIN_PAGE);
                action = ActionForward.getAction(this, request, mainPage);
            } else {
                log.info("Redirect to user-login");
                action = ActionForward.getAction(this, request, "index?t=t&page=login");
            }
        }
        return action;
    }

}
