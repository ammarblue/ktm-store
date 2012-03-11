package org.ktm.tag.auth;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class IsUserLoggedInTag extends TagSupport {

    private static final long serialVersionUID = 1L;

    @Override
    public int doStartTag() throws JspException {

        try {
            if(IsUserTagsImpl.isUserLogedIn(this.pageContext)) {
                return EVAL_BODY_INCLUDE;
            } else {
                return SKIP_BODY;
            }
        } catch (AuthException e) {
            throw new JspException("No session object");
        }
    }
}
