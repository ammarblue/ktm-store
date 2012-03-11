package org.ktm.tag.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class EnforceLoginTag extends TagSupport {

    private static final long serialVersionUID = 1L;

    private String            loginPage;

    public EnforceLoginTag() {
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    @Override
    public int doEndTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();

        try {
            if (AuthenticatorFactory.isUserLoggedIn()) {
                return EVAL_PAGE;
            }

            AuthenticatorFactory.saveRequestContext(request);
        } catch (AuthException e) {
            throw new JspException("Authentication error, no session object");
        }

        try {
            pageContext.forward(loginPage);
        } catch (Exception ex) {
            throw new JspException(ex.getMessage());
        }

        return SKIP_PAGE;
    }

    @Override
    public void release() {

    }
}
