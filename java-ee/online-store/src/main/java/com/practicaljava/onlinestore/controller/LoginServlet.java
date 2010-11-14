package com.practicaljava.onlinestore.controller;

import com.practicaljava.onlinestore.Message;
import com.practicaljava.onlinestore.service.UserService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @EJB
    private UserService userService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
                                                                                          ServletException {

        if (request.getParameter("error") != null)
            Message.setErrorMessage(request, "Wrong Credentials");
        
        request.getRequestDispatcher("/view/login.jspx").forward(request, response);
    }
}
