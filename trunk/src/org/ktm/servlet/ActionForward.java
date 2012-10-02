package org.ktm.servlet;

import javax.servlet.http.HttpServletRequest;

public class ActionForward {
    private String  forwardUri;
    private boolean isRedirect;
    private boolean isAction;

    private ActionForward(String forwardUri) {
        this.forwardUri = forwardUri;
    }

    public String getForwardUri() {
        return forwardUri;
    }

    public void setForwardUri(String forwardUri) {
        this.forwardUri = forwardUri;
    }

    public static ActionForward getAction(AbstractServlet servlet, HttpServletRequest request, String uri) {
        ActionForward action = new ActionForward(uri);
        action.isAction = true;
        return action;
    }

    public static ActionForward getAction(AbstractServlet servlet, HttpServletRequest request, String uri, boolean isRedirect) {
        ActionForward action = getAction(servlet, request, uri);
        action.isRedirect = isRedirect;
        action.isAction = true;
        return action;
    }

    public boolean isRedirect() {
        return isRedirect;
    }

    public static ActionForward getUri(AbstractServlet servlet, HttpServletRequest request, String uri) {
        return new ActionForward(servlet.getBasePath(request) + uri);
    }

    public boolean isAction() {
        return isAction;
    }
}
