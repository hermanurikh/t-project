package ru.tsystems.tproject.controllers;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.services.API.OptionService;
import ru.tsystems.tproject.services.API.UserService;
import ru.tsystems.tproject.services.implementation.OptionServiceImplementation;
import ru.tsystems.tproject.services.implementation.UserServiceImplementation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * This servlet shows a list of all users on employee's page.
 */
public class UsersServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(UsersServlet.class);


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {

        response.setContentType("text/html;charset=utf-8");
        try {
            UserService userService = new UserServiceImplementation();
            List<User> usersList = userService.getAllUsers();
            request.getSession().setAttribute("usersList", usersList);
            response.sendRedirect("../cp_employee/cp_employee_users.jsp");
        }
        catch (Exception ex) {
            logger.error(ex);
            request.getSession().setAttribute("exception", ex);
            response.sendRedirect("../cp_employee/exception.jsp");
        }
    }
}
