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
            String surname =  request.getParameter("surname");
            Date birthday = dateFormat.parse(request.getParameter("birthday"));
            String passport = request.getParameter("passport");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String login = request.getParameter("login");
            int balance = Integer.parseInt(request.getParameter("balance"));
            String password = request.getParameter("password"); // добавить конвертер в MD5!
            int role = Integer.parseInt(request.getParameter("cb"));
            userService.createUser(new User(name, surname, birthday, passport, address, email, login, balance, password, roleService.getRoleById(role)));
            response.sendRedirect("../cp_employee/success.html");
        }
        catch (Exception ex) {
            logger.error(ex);
            request.getSession().setAttribute("exception", ex);
            response.sendRedirect("../cp_employee/exception.jsp");
        }




    }
}