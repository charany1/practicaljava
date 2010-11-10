package com.practicaljava.lesson37.jms;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/send")
public class SendMessage extends HttpServlet {

    @EJB
    private MessageService messageService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,
                                                                                           ServletException {
        String text = request.getParameter("text");
        messageService.sendMessage(text);

        response.sendRedirect("index.html");
    }
}
