package org.ktm.tag.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class TracUriTag extends TagSupport {

    private static final long serialVersionUID = 1L;

    public TracUriTag() {

    }

    @Override
    public int doEndTag() throws JspException {
        try {
            AuthenticatorFactory.saveRequestContext((HttpServletRequest) pageContext.getRequest());
        } catch (AuthException e) {
            throw new JspException("No session object");
        }
        return EVAL_PAGE;
    }
}
