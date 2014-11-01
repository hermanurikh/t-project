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

/**
 * Created by german on 26.10.14.
 */
public class UserIncreaseBalanceServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UserIncreaseBalanceServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        UserService userService = new UserServiceImplementation();
        try {
            User user = (User) request.getSession().getAttribute("currentUserU");
            int balance = user.getBalance() + 500;
            user.setBalance(balance);
            userService.updateUser(user);
            request.getSession().setAttribute("currentUserU", user);
            response.sendRedirect("../cp_client/cp_client_balance.jsp");


        } catch (Exception ex) {
            logger.error(ex);
            request.getSession().setAttribute("exception", ex);
            response.sendRedirect("../cp_client/exception.jsp");


        }

    }
}
