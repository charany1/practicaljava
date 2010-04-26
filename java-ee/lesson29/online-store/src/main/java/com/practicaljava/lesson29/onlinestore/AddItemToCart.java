package com.practicaljava.lesson29.onlinestore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns={"/add"})
public class AddItemToCart extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        String productCode = request.getParameter("productCode");

        Item item = Store.getInstance().getItem(productCode);

        if (item == null) {
            Message.setErrorMessage(request, "Item with code '" + productCode + "' was not found");
            response.sendRedirect("items");
            return;
        }

        User user = (User) request.getSession().getAttribute("user");
        user.getCartItems().add(item);

        Message.setInfoMessage(request, "Item '" + item.getName() + "' was added to cart");
        response.sendRedirect("items");
    }
}