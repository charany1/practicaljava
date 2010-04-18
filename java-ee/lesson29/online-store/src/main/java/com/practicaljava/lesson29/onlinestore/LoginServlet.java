package com.practicaljava.lesson29.onlinestore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="login", urlPatterns={"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        String name = request.getParameter("name");

        if (name != null && name.equals("guest")) {
            User user = new User();
            user.setId(name);
            user.setRewardPoints(150);

            request.getSession().setAttribute("user", user);

            String lastUnauthorisedPage = (String) request.getSession().getAttribute("lastUnauthorisedPage");
            if (lastUnauthorisedPage != null)
                response.sendRedirect(lastUnauthorisedPage);
            else
                response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("loginFailure.jsp");
        }

    }
}
