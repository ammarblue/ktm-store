package org.ktm.tag;

import java.text.ParseException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.ktm.domain.party.Party;
import org.ktm.tag.auth.AuthException;
import org.ktm.tag.auth.IsUserTagsImpl;
import org.ktm.web.utils.DateUtils;

public class KTMInfoTag extends TagSupport {

    private static final long serialVersionUID = -5471969313089458126L;
    
    private String method;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
    public int doStartTag() {
        try {
          JspWriter out = pageContext.getOut();
          out.println(infoByMethod());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return SKIP_BODY;
    }
    
    private String infoByMethod() throws ParseException, JspException {
        String result = "";
        if (method.equals("date")) {
            result =  DateUtils.formatNowDate();
        } else if (method.equals("user")) {
            Party party;
            try {
                party = IsUserTagsImpl.getParty(pageContext);
            } catch (AuthException e) {
                throw new JspException("No session object");
            }
            if (party != null) {
                return party.getLabel();
            }
        }
        return result;
    }

}
