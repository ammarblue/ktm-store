package org.ktm.tags.auth;

import java.util.Vector;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class IsUserInRolesTag extends TagSupport {

    private static final long serialVersionUID = 1L;

    protected Vector<String> roles; /* default access */

    public void setRoles(String roleString) {
        String subString;
        int index;
        roles = new Vector<String>();
        if(roleString.indexOf(',') == -1 ) {
            roles.add(roleString);
        } else {
            do {
                index = roleString.indexOf(',');
                if(index != -1 ) {
                    subString=roleString.substring(0,index);
                    roleString=roleString.substring(index +1 );
                    roles.add(subString);
                } else {
                    roles.add(roleString);
                    roleString="";
                }
            } while ( roleString.length() > 0 );
        }
    }

    @Override
    public int doStartTag() throws JspException {
        if (IsUserTagsImpl.isUserInRoles(this.pageContext, roles)) {
            return EVAL_BODY_INCLUDE;
        } else {
            return SKIP_BODY;
        }
    }
    
}
