package com.practicaljava.lesson29.onlinestore.controller;

import com.practicaljava.lesson29.onlinestore.RoleType;
import com.practicaljava.lesson29.onlinestore.model.User;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns={"/*"})
public class LoginFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        if (! (request instanceof HttpServletRequest)) {
            chain.doFilter(request, response);
            return;
        }

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            user = new User(RoleType.ANONYMOUS);
            session.setAttribute("user", user);
        }

        if (httpRequest.getRequestURI().indexOf("login") != -1) {
            chain.doFilter(request, response);
            return;
        }

        if (! isAllowed(user, httpRequest.getRequestURI())) {
            httpRequest.getRequestDispatcher("/view/login.jspx").forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
    }

    private boolean isAllowed(User user, String url) {
        return true;
    }
}
