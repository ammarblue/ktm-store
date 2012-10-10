package org.ktm.web.tags;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class IfConditionTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		
		IfTag parent = (IfTag) findAncestorWithClass(this, IfTag.class);
		if (parent == null) {
			throw new JspTagException("Condition not inside if");
		}
		
		StringWriter stringWriter = new StringWriter();
		getJspBody().invoke(stringWriter);
		String bodyString = stringWriter.toString();
		
		parent.setHasCondition(true);
		
		if (bodyString.trim().equals("true")) {
			parent.setCondition(true);
		} else {
			parent.setCondition(false);
		}
	}
}
