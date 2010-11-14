package com.practicaljava.onlinestore.controller;

import com.practicaljava.onlinestore.model.Item;
import com.practicaljava.onlinestore.service.ItemService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/newItem")
public class AddItemToStoreServlet extends HttpServlet {

    @EJB
    private ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
                                                                                            ServletException {

        Item item = new Item();
        item.setName("NewName");
        item.setDescription("NewDescription");
        item.setPriceInPoints(102);

        itemService.addItem(item);
        
        response.sendRedirect("items");
    }
}
