package org.ktm.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class IfElseTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException {
		IfTag parent = (IfTag) findAncestorWithClass(this, IfTag.class);
		if (parent == null) {
			throw new JspException("Then not inside if");
		} else if (!parent.isHasCondition()) {
			throw new JspException("Condition tag must come before Then tag");
		}
		
		if (!parent.isCondition()) {
			try {
				getJspBody().invoke(null);
			} catch (IOException e) {
				System.out.println("Error in IfElseTag: " + e);
			}
		}
	}
}
