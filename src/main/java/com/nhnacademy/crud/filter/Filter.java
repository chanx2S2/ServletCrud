package com.nhnacademy.crud.filter;

import jakarta.servlet.*;

import java.io.IOException;

public interface Filter {
    void init(FilterConfig filterConfig) throws ServletException;
    void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException;
}
