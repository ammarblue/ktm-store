package org.ktm.web.tabs;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class IfTag extends SimpleTagSupport {
	
	private boolean condition;
	private boolean hasCondition;

	@Override
	public void doTag() throws JspException, IOException {
		getJspBody().invoke(null);
	}

	public boolean isCondition() {
		return condition;
	}

	public void setCondition(boolean condition) {
		this.condition = condition;
	}

	public boolean isHasCondition() {
		return hasCondition;
	}

	public void setHasCondition(boolean hasCondition) {
		this.hasCondition = hasCondition;
	}
}
