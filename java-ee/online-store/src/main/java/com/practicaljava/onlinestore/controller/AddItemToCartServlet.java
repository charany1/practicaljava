package com.practicaljava.onlinestore.controller;

import com.practicaljava.onlinestore.Message;
import com.practicaljava.onlinestore.RequestParameters;
import com.practicaljava.onlinestore.model.Cart;
import com.practicaljava.onlinestore.model.Item;
import com.practicaljava.onlinestore.service.ItemService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addItem")
public class AddItemToCartServlet extends HttpServlet {

    @EJB
    private ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
                                                                                          ServletException {

        Long productCode = RequestParameters.getLong(request, "productCode");

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

        Cart cart = (Cart) request.getSession().getAttribute("cart");
        cart.addItem(item);

        Message.setInfoMessage(request, "Item '" + item.getName() + "' was added to cart");
        response.sendRedirect("items");
    }
}
