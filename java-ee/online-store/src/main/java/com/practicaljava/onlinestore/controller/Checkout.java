package com.practicaljava.onlinestore.controller;

import com.practicaljava.onlinestore.Message;
import com.practicaljava.onlinestore.model.Cart;
import com.practicaljava.onlinestore.model.User;
import com.practicaljava.onlinestore.service.UserService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/checkout")
public class Checkout extends HttpServlet {

    @EJB
    private UserService userService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
                                                                                           ServletException {

        String userPrincipalName = request.getUserPrincipal().getName();
        User user = userService.userById(userPrincipalName);

        if (user == null) {
            Message.setErrorMessage(request, "User '" + userPrincipalName + "' is no longer valid");
            response.sendRedirect("items");
            return;
        }

        Cart cart = (Cart) request.getSession().getAttribute("cart");

        if (cart == null) {
            Message.setErrorMessage(request, "We are sorry, your cart was expired");
            response.sendRedirect("items");
            return;
        }

        String creditCardNumber = request.getParameter("creditCardNumber");
        String billingAddress = request.getParameter("billingAddress");
        String secretCardCode = request.getParameter("secretCardCode");

        Map<String, String> errorMessages = new HashMap<String, String>();

        if (creditCardNumber.length() != 16)
            errorMessages.put("creditCardNumber", "We proceed only 16 digits Visa cards");

        if (secretCardCode.length() != 3)
            errorMessages.put("secretCardCode", "3 digits secret code");

        if (billingAddress.length() < 15)
            errorMessages.put("billingAddress", "Please enter your address");
        else if (billingAddress.length() > 100)
            errorMessages.put("billingAddress", "Must be no more then 100 symbols");

        if (! errorMessages.isEmpty()) {
            Message.setErrorMessage(request, "Please adjust information in the marked fields below");
            Message.setErrorMessages(request, errorMessages);

            // pass back previously entered fields
            // to allow user to fix some of them
            //
            request.setAttribute("creditCardNumber", creditCardNumber);
            request.setAttribute("billingAddress", billingAddress);

            request.getRequestDispatcher("/view/checkout.jspx").forward(request, response);
        }
        else {
            Message.setInfoMessage(request, "Your credit card will be charged soon on " + cart.getTotalPrice() + " points");
            response.sendRedirect("items");

            cart.clear();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
                                                                                          ServletException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        if (cart == null) {
            Message.setErrorMessage(request, "We are sorry, your cart was expired");
            response.sendRedirect("items");
            return;
        }

        if (cart.getItems().isEmpty()) {
            Message.setErrorMessage(request, "Your cart is empty");
            response.sendRedirect("items");
            return;
        }

        request.getRequestDispatcher("/view/checkout.jspx").forward(request, response);
    }
}
