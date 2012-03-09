package org.ktm.tag.auth;

import javax.servlet.jsp.JspException;

public class IsUserNotInRolesTag extends IsUserInRolesTag {

    private static final long serialVersionUID = 1L;

    @Override
    public int doStartTag() throws JspException {
        if (IsUserTagsImpl.isUserNotInRoles(this.pageContext, roles)) {
            return EVAL_BODY_INCLUDE;
        } else {
            return SKIP_BODY;
        }
    }
}