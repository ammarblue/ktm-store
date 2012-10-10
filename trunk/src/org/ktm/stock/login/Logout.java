package org.ktm.stock.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.ktm.servlet.AbstractServlet;
import org.ktm.servlet.ActionForward;
import org.ktm.web.bean.FormBean;
import org.ktm.web.tags.auth.AuthException;
import org.ktm.web.tags.auth.Authenticator;
import org.ktm.web.tags.auth.AuthenticatorFactory;

@WebServlet("/logout")
public class Logout extends AbstractServlet {

    private static final long serialVersionUID = 592209854904210873L;
    private final Logger      log              = Logger.getLogger(Logout.class);

    @Override
    protected ActionForward processRequest(FormBean form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("Processing logout...");

        try {
            Authenticator auth = AuthenticatorFactory.getAuthComponentNoCreate(request.getSession());
            if (auth != null) {
                auth.doLogout();
                AuthenticatorFactory.setUserLoggingOut(request);
            }
        } catch (AuthException e) {
            e.printStackTrace();
        }
        return null;
    }

}
