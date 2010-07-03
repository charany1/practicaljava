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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/registration")
public class Registration extends HttpServlet {

    @EJB
    private UserService userService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
                                                                                           ServletException {

        String userId = request.getParameter("userId");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        Map<String, String> errorMessages = new HashMap<String, String>();

        if (password.length() < 6)
            errorMessages.put("password", "Must be at least 6 symbols");
        else if (password.length() > 100)
            errorMessages.put("password", "Must be no more then 100 symbols");            
        else if (! password.equals(confirmPassword))
            errorMessages.put("confirmPassword", "Confirm password doesn't match password");

        // it is not a real email validation
        // in any case email has to be validated via sending email with a link
        if (!email.contains("@"))
            errorMessages.put("email", "Please enter your email");
        else if (email.length() > 100)
            errorMessages.put("email", "Must be no more then 100 symbols");

        if (address.length() < 15)
            errorMessages.put("address", "Please enter your address");
        else if (address.length() > 100)
            errorMessages.put("address", "Must be no more then 100 symbols");

        if (userId.length() < 2) {
            errorMessages.put("userId", "User id must be at least 2 symbols");
        }
        else if (userId.length() > 100) {
            errorMessages.put("userId", "Must be no more then 100 symbols");
        }
        else {
            User registeredUser = userService.userById(userId);
            if (registeredUser != null)
                errorMessages.put("userId", "Sorry, user with this name already registered");
        }

        if (! errorMessages.isEmpty()) {
            Message.setErrorMessage(request, "Please adjust information in the marked fields below");
            Message.setErrorMessages(request, errorMessages);

            // pass previously entered fields
            // to allow user fix some of them
            //
            request.setAttribute("userId", userId);
            request.setAttribute("address", address);
            request.setAttribute("email", email);

            request.getRequestDispatcher("/view/registration.jspx").forward(request, response);
        }
        else {
            User user = new User();
            user.addRole(RoleType.USER);
            user.setId(userId);
            user.setAddress(address);
            user.setPassword(password);
            user.setEmail(email);

            userService.addUser(user);

            Message.setInfoMessage(request, "User '" + userId + "' was successfully registered.");
            response.sendRedirect("items");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
                                                                                          ServletException {

        request.getRequestDispatcher("/view/registration.jspx").forward(request, response);
    }
}