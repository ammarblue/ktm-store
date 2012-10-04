package org.ktm.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import org.ktm.core.KTMContext;
import org.ktm.utils.HibernateSessionUtil;

@WebFilter("/*")
public class RequestWrapperFilter implements Filter {

    public RequestWrapperFilter() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        AddableHttpRequest newRequest = new AddableHttpRequest(httpRequest);
        request = newRequest;

        KTMContext.setSession(httpRequest.getSession());

        try {
            // Bind (connect) Hibernate Session instances
            bindSessions(httpRequest);
            // Pass the request to the rest of the filter chain
            chain.doFilter(request, response);
            // Unbind (disconnect) Hibernate Session instances
            unbindOrCloseSessions(httpRequest, false);
        } catch (Exception ex) {
            // Exception occurred. Unbind, close and remove all sessions
            unbindOrCloseSessions(httpRequest, true);
            // Rethrow exception
            throw new ServletException(ex);
        }
    }

    private void unbindOrCloseSessions(HttpServletRequest httpRequest, boolean forceClose) {
        HibernateSessionUtil.unbindEntitySession(httpRequest, forceClose);
    }

    private void bindSessions(HttpServletRequest httpRequest) {
        HibernateSessionUtil.bindEntitySession(httpRequest);

    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

}
