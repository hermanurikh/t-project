package ru.tsystems.tproject.controllers;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.services.API.RoleService;
import ru.tsystems.tproject.services.API.UserService;
import ru.tsystems.tproject.services.implementation.RoleServiceImplementation;
import ru.tsystems.tproject.services.implementation.UserServiceImplementation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This servlet is responsible for creating a new user, redirecting to success/error page. The params are got from the session.
 */
public class NewUserServlet extends HttpServlet {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static Logger logger = Logger.getLogger(NewUserServlet.class);
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImplementation();
        RoleService roleService = new RoleServiceImplementation();
        try {
            String name = request.getParameter("name");
            logger.error(name);
            String surname =  request.getParameter("surname");
            logger.error(surname);
            Date birthday = dateFormat.parse(request.getParameter("birthday"));
            logger.error(birthday);
            String passport = request.getParameter("passport");
            logger.error(passport);
            String address = request.getParameter("address");
            logger.error(address);
            String email = request.getParameter("email");
            logger.error(email);
            String login = request.getParameter("login");
            logger.error(login);
            int balance = Integer.parseInt(request.getParameter("balance"));
            logger.error(balance);
            String password = request.getParameter("password"); // добавить конвертер в MD5!
            logger.error(password);
            int role = Integer.parseInt(request.getParameter("cb"));
            logger.error(role);
            userService.createUser(new User(name, surname, birthday, passport, address, email, login, balance, password, roleService.getRoleById(role)));
            logger.error("user created");
            response.sendRedirect("../cp_employee/success.html");
        }
        catch (Exception ex) {
            logger.error(ex);
            response.sendRedirect("../cp_employee/exception.html");
        }




    }
}