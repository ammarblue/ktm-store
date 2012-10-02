package org.ktm.tags.auth;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class IsUserInRoleTag extends TagSupport {

    private static final long serialVersionUID = 1L;

    protected String role = ""; /* default access */

    public void setRole(String role) {
        this.role = role;
    }
    
    @Override
    public int doStartTag() throws JspException {

        if(IsUserTagsImpl.isUserInRole(this.pageContext, role)) {
            return EVAL_BODY_INCLUDE;
        } else {
            return SKIP_BODY;
        }
    }
}
