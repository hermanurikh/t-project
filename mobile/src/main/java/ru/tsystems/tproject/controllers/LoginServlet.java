package ru.tsystems.tproject.controllers;


import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.services.API.UserService;
import ru.tsystems.tproject.services.implementation.UserServiceImplementation;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This is a login servlet that dispatches the initial requests on employee or client pages in the event of success authorization.
 */
public class LoginServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(LoginServlet.class);
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        UserService userService = new UserServiceImplementation();
        try {
            String username = request.getParameter("username");
            if (username.length() > 100) throw new Exception("Login is too long");
            else {
                String password = Converter.getMD5(request.getParameter("password"));
                User user = userService.getUserByLogin(username);
                if (user == null) throw new Exception("There is no user with the login " + username);
                else {
                    if (user.getPassword().equals(password)) {
                        if (user.getRole().getId() == 1) {
                            request.getSession().setAttribute("currentUserU", user);
                            response.sendRedirect("../cp_client/cp_client_tariff.html");
                        } else if (user.getRole().getId() == 2) {
                            request.getSession().setAttribute("currentUserU", user);
                            response.sendRedirect("../cp_employee/cp_employee_main.html");
                        } else {
                            throw new Exception("The role of user is undefined");
                        }
                    } else throw new Exception("The users passwords do not match");
                }
            }
        }
        catch (Exception exception) {
            request.getSession().invalidate();
            request.getSession().setAttribute("isInputValid", "false");
            logger.error(exception);
            response.sendRedirect("../login.jsp");
        }

    }
}
