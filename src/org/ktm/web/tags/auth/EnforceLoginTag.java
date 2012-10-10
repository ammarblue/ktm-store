package org.ktm.web.tags.auth;

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

        if (AuthenticatorFactory.isUserLoggedIn(request)) {
            return EVAL_PAGE;
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
