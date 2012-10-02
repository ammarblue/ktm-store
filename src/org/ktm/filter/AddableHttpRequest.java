package org.ktm.filter;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class AddableHttpRequest extends HttpServletRequestWrapper {

	private HashMap<String, String> params = new HashMap<String, String>();

	public AddableHttpRequest(HttpServletRequest request) {
		super(request);
	}

	@Override
	public String getParameter(String name) {
		// if we added one, return that one
		if (params.get(name) != null) {
			return params.get(name);
		}
		// otherwise return what's in the original request
		HttpServletRequest req = (HttpServletRequest) super.getRequest();
		return validate(name, req.getParameter(name));
	}

	private String validate(String name, String parameter) {
		return parameter;
	}

	public void addParameter(String name, String value) {
		params.put(name, value);
	}
}