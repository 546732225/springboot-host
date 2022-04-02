package com.example.data.filter;

import javax.servlet.*;
import java.io.IOException;

//@WebFilter(filterName = "xssFilter", urlPatterns = "/*")
public class XssFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        filterChain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) servletRequest), servletResponse);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
    }

}

