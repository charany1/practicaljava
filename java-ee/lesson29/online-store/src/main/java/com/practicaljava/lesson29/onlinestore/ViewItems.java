package com.practicaljava.lesson29.onlinestore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns={"/items"})
public class ViewItems extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        request.setAttribute("items", Store.getInstance().getItems());

        request.getRequestDispatcher("/pages/items.jspx").forward(request, response);
    }
}