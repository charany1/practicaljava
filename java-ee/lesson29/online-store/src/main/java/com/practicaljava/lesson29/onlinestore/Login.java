package com.practicaljava.lesson29.onlinestore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(urlPatterns={"/login"})
public class Login extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        String id = request.getParameter("id");

        if (id == null) {
            request.getRequestDispatcher("/pages/login.jspx").forward(request, response);
            return;
        }

        if (determineRoleById(id) == RoleType.USER) {
            User user = (User) request.getSession().getAttribute("user");
            user.setRole(RoleType.USER);
            user.setId(id);

            request.getSession().setAttribute("user", user);
            response.sendRedirect("items");
        } else {
            Message.setErrorMessage(request, "Wrong id: " + id);
            request.getRequestDispatcher("/pages/login.jspx").forward(request, response);
        }
    }

    private RoleType determineRoleById(String id) {
        if (validUserIds.contains(id))
            return RoleType.USER;

        return RoleType.ANONYMOUS;
    }

    private static List<String> validUserIds = Arrays.asList("teacher", "student", "guest");
}
