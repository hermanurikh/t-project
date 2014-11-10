package ru.tsystems.tproject.controllers;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.Contract;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.services.API.UserService;
import ru.tsystems.tproject.services.implementation.UserServiceImplementation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * This servlet deletes a user from the database.
 */
public class DeleteEmployeeServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(DeleteEmployeeServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=utf-8");
        UserService userService = new UserServiceImplementation();
        try {
            int userId = Integer.parseInt(request.getParameter("id"));
            User user = userService.getEntityById(userId);
            List<Contract> contracts = user.getContracts();
            if (contracts.isEmpty()) {
                userService.deleteEntity(user);
            } else throw new Exception("Before deleting a user you should delete all his contracts.");
            request.getSession().setAttribute("usersList", userService.getAll());
            response.sendRedirect("../cp_employee/cp_employee_users.jsp");
        }
        catch (Exception ex) {
            logger.error(ex);
            request.getSession().setAttribute("exception", ex);
            response.sendRedirect("../cp_employee/exception.jsp");
        }
    }
}
