package com.practicaljava.lesson29.onlinestore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="addToCart", urlPatterns={"/add"})
public class AddToCartServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        String itemId = (String) request.getParameter("id");
        Long id = Long.valueOf(itemId);

        if (id == null) {
            response.sendRedirect("itemNotFound.jsp");
            return;
        }

        User user = (User) request.getSession().getAttribute("user");

        StoreItems storeItems = (StoreItems) request.getServletContext().getAttribute("storeItems");
        Item item = storeItems.getItem(id);

        if (item == null) {
            response.sendRedirect("itemNotFound.jsp");
            return;
        }

        user.setRewardPoints(user.getRewardPoints() - item.getPriceInPoints());
        user.getCartItems().add(item);
        
        response.sendRedirect("index.jsp");
    }
}