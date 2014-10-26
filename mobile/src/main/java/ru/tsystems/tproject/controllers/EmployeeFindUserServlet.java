package ru.tsystems.tproject.controllers;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.services.API.UserService;
import ru.tsystems.tproject.services.implementation.UserServiceImplementation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This servlet parses the info from the cp_employee_user_find page and redirects to the page where you can change the user's data.
 */
public class EmployeeFindUserServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(EmployeeFindUserServlet.class);

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("EmployeeUserChangeServlet");
        UserService userService = new UserServiceImplementation();
        String number = request.getParameter("number");
        String login = request.getParameter("login");
        User user;
        try {
            if (number == null || number.equals("")) {
                user = userService.getUserByLogin(login);
            }
            else {
                long userNumber = Long.parseLong(number);
                user = userService.getUserByNumber(userNumber);
            }
            request.getSession().setAttribute("found", "true");
            request.getSession().setAttribute("id", user.getId());
            request.setAttribute("id", user.getId());
            requestDispatcher.forward(request, response);
        }
        catch (Exception ex) {
            logger.error(ex);
            request.getSession().setAttribute("found", "false");
            response.sendRedirect("../cp_employee/cp_employee_user_find.jsp");
        }

    }
}
