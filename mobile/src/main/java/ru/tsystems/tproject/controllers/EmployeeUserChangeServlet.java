package ru.tsystems.tproject.controllers;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.services.API.UserService;
import ru.tsystems.tproject.services.implementation.UserServiceImplementation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * This servlet gives out a form that helps to change a user's data on the page cp_employee_user_data_change.jsp
 *
 */
public class EmployeeUserChangeServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(EmployeeUserChangeServlet.class);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    UserService userService = new UserServiceImplementation();
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        try {
            int userId = Integer.parseInt(String.valueOf(request.getSession().getAttribute("id"))); //.getParameter("id"));
            User user = userService.getUserById(userId);
            request.getSession().setAttribute("id", user.getId());
            request.getSession().setAttribute("name", user.getName());
            request.getSession().setAttribute("surname", user.getSurname());
            request.getSession().setAttribute("birthday", dateFormat.format(user.getBirthday()));
            request.getSession().setAttribute("passport", user.getPassport());
            request.getSession().setAttribute("address", user.getAddress());
            request.getSession().setAttribute("email", user.getEmail());
            request.getSession().setAttribute("login", user.getLogin());
            request.getSession().setAttribute("balance", user.getBalance());
            request.getSession().setAttribute("role", user.getRole().getId());
            response.sendRedirect("../cp_employee/cp_employee_user_data_change.jsp");
        }
        catch (Exception ex) {
            logger.error(ex);
            request.getSession().setAttribute("exception", ex);
            response.sendRedirect("../cp_employee/exception.jsp");
        }

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        try {
            int userId = Integer.parseInt(request.getParameter("id"));
            User user = userService.getUserById(userId);
            request.getSession().setAttribute("id", user.getId());
            request.getSession().setAttribute("name", user.getName());
            request.getSession().setAttribute("surname", user.getSurname());
            request.getSession().setAttribute("birthday", dateFormat.format(user.getBirthday()));
            request.getSession().setAttribute("passport", user.getPassport());
            request.getSession().setAttribute("address", user.getAddress());
            request.getSession().setAttribute("email", user.getEmail());
            request.getSession().setAttribute("login", user.getLogin());
            request.getSession().setAttribute("balance", user.getBalance());
            request.getSession().setAttribute("role", user.getRole().getId());
            response.sendRedirect("../cp_employee/cp_employee_user_data_change.jsp");
        }
        catch (Exception ex) {
            logger.error(ex);
            request.getSession().setAttribute("exception", ex);
            response.sendRedirect("../cp_employee/exception.jsp");
        }

    }
}

