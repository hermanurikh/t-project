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
 * Created by german on 10/21/14.
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
            /*RequestDispatcher rd = request.getRequestDispatcher("../cp_employee/cp_employee_options.jsp");
            rd.forward(request, response);*/
            response.sendRedirect("../cp_employee/cp_employee_users.jsp");
        }
        catch (Exception ex) {
            response.sendRedirect("../cp_employee/exception.html");
        }
    }
}
