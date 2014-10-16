package ru.tsystems.tproject.servlets;


import ru.tsystems.tproject.entities.LoginDispatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by german on 15.10.14.
 */
public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RequestDispatcher rd;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (LoginDispatcher.dispatch())
            rd = request.getRequestDispatcher("cp_client/cp_client_main.jsp");
        else rd = request.getRequestDispatcher("login.html");
        rd.forward(request, response);

    }
}
