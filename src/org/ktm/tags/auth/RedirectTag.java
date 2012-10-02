package org.ktm.tags.auth;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class RedirectTag extends TagSupport {

    private static final long serialVersionUID = 1L;

    private String page;

    public RedirectTag()   {
    }

    public void setPage(String page) {
        this.page = page;
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            pageContext.forward(page);
        } catch(Exception ex) {
            throw new JspException(ex.getMessage());
        }

        return SKIP_PAGE;
    }

    @Override
    public void release() {
    }
}
