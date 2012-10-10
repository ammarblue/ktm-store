package org.ktm.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.ktm.web.tabs.auth.AuthenticatorFactory;

public abstract class SecureServlet extends AbstractServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected boolean prepareRequest(HttpServletRequest request) throws ServletException, IOException {
        super.prepareRequest(request);
        AuthenticatorFactory.saveRequestContext(request);
        return AuthenticatorFactory.isUserLoggedIn(request);
    }
}
