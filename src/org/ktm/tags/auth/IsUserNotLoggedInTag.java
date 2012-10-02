package org.ktm.tags.auth;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class IsUserNotLoggedInTag extends TagSupport {

    private static final long serialVersionUID = 1L;

    @Override
    public int doStartTag() throws JspException {

        if(IsUserTagsImpl.isUserLogedIn(this.pageContext)) {
            return SKIP_BODY;
        } else {
            return EVAL_BODY_INCLUDE;
        }
    }
}
