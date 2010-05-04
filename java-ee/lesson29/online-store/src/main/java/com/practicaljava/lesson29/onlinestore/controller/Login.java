package com.practicaljava.lesson29.onlinestore.controller;

import com.practicaljava.lesson29.onlinestore.Message;
import com.practicaljava.lesson29.onlinestore.RoleType;
import com.practicaljava.lesson29.onlinestore.model.User;
import com.practicaljava.lesson29.onlinestore.service.UserService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns={"/login"})
public class Login extends HttpServlet {

    @EJB
    private UserService userService;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        String id = request.getParameter("id");
        String password = request.getParameter("password");

        if (id == null) {
            request.getRequestDispatcher("/view/login.jspx").forward(request, response);
            return;
        }

        User user = userService.userById(id);

        if (user != null && user.getRole() == RoleType.USER) {
            User sessionUser = (User) request.getSession().getAttribute("user");
            sessionUser.setRole(RoleType.USER);
            sessionUser.setId(id);

            request.getSession().setAttribute("user", sessionUser);
            response.sendRedirect("items");
        } else {
            Message.setErrorMessage(request, "Wrong id: " + id);
            request.getRequestDispatcher("/view/login.jspx").forward(request, response);
        }
    }
}
