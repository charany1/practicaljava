package com.practicaljava.onlinestore.controller;

import com.practicaljava.onlinestore.Message;
import com.practicaljava.onlinestore.RequestParameters;
import com.practicaljava.onlinestore.model.Item;
import com.practicaljava.onlinestore.model.User;
import com.practicaljava.onlinestore.service.ItemService;
import com.practicaljava.onlinestore.service.UserService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addReview")
public class AddItemReview extends HttpServlet {

    @EJB
    private UserService userService;

    @EJB
    private ItemService itemService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
                                                                                           ServletException {

        Long productCode = RequestParameters.getLong(request, "productCode");

        String reviewText = request.getParameter("reviewText");
        Long reviewScore = RequestParameters.getLong(request, "reviewScore");

        if (reviewScore == null || reviewScore < 1 || reviewScore > 5) {
            Message.setErrorMessage(request, "Review score error");
            response.sendRedirect("items");
            return;
        }

        if (productCode == null) {
            Message.setErrorMessage(request, "Item code is not correct");
            response.sendRedirect("items");
            return;
        }

        Item item = itemService.itemByCode(productCode);

        if (item == null) {
            Message.setErrorMessage(request, "Item with code '" + productCode + "' was not found");
            response.sendRedirect("items");
            return;
        }

        String userPrincipalName = request.getUserPrincipal().getName();
        User user = userService.userById(userPrincipalName);

        if (user == null) {
            Message.setErrorMessage(request, "We are sorry, user '" + userPrincipalName + "' is no longer valid");
            response.sendRedirect("items");
            return;
        }

        item.addReview(user, reviewScore.intValue(), reviewText);

        itemService.updateItem(item);

        Message.setInfoMessage(request, "Review for '" + item.getName() + "' was added to cart");
        response.sendRedirect("item?productCode=" + productCode);
    }
}
