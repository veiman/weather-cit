package ru.veiman.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by veiman. (04.09.15 11:03)
 */
public class StaticResourcesFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String contextPath = ((HttpServletRequest) servletRequest).getContextPath();
        String requestURI = httpRequest.getRequestURI();

        int pos = requestURI.indexOf(contextPath);
        if (pos != -1) {
            requestURI = requestURI.substring(pos+contextPath.length());
        }

        if (requestURI.equals("/")) {
            requestURI = "/index.html";
        }
        String newURI = "/dist" + requestURI;
        servletRequest.getRequestDispatcher(newURI).forward(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
