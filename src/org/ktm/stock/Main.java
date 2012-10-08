package org.ktm.stock;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ktm.servlet.ActionForward;
import org.ktm.servlet.SecureServlet;
import org.ktm.stock.bean.FormBean;
import org.ktm.stock.bean.MainBean;
import org.ktm.utils.Globals;

@WebServlet("/Main")
public class Main extends SecureServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public String getBeanClass() {
        return "org.ktm.stock.bean.MainBean";
    }

    @Override
    protected ActionForward processRequest(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forwordUri = request.getServletContext().getInitParameter("jspMainPage");
        ActionForward action = null;
        MainBean bean = (MainBean) form;

        if (bean.getPage() != null) {
            ServletContext context = request.getServletContext();
            String basePath = context.getInitParameter(Globals.BASE_PATH);
            bean.setPageContent(basePath + bean.getPage() + "_content.jsp");
            request.getSession().setAttribute("page", bean.getPage());
            action = ActionForward.getUri(this, request, forwordUri);
        } else {
            throw new ServletException("Page not found !!");
        }
        return action;
    }
}
