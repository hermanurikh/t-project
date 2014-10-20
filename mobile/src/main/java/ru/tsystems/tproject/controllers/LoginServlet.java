package ru.tsystems.tproject.controllers;


import ru.tsystems.tproject.services.LoginDispatcher;

import javax.servlet.*;
import javax.servlet.http.Cookie;
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
        eraseCookie(request, response);
        if (LoginDispatcher.dispatch())
            response.sendRedirect("../cp_client/cp_client_tariff.html");
        //else rd = request.getRequestDispatcher("../login.html");
        //rd.forward(request, response);
        else response.sendRedirect("../login.html");

    }

    private void eraseCookie(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null)
            for (int i = 0; i < cookies.length; i++) {
                cookies[i].setValue("");
                cookies[i].setPath("/");
                cookies[i].setMaxAge(0);
                resp.addCookie(cookies[i]);
            }
    }
}
