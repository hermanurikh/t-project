package ru.tsystems.tproject.controllers;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.services.API.RoleService;
import ru.tsystems.tproject.services.API.UserService;
import ru.tsystems.tproject.services.implementation.RoleServiceImplementation;
import ru.tsystems.tproject.services.implementation.UserServiceImplementation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This servlet changes the user's data from the page cp_employee_user_data_change.jsp
 */
public class EmployeeUserDataChangeServlet extends HttpServlet {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static Logger logger = Logger.getLogger(EmployeeUserDataChangeServlet.class);

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImplementation();
        RoleService roleService = new RoleServiceImplementation();
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            Date birthday = dateFormat.parse(request.getParameter("birthday"));
            String passport = request.getParameter("passport");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String login = request.getParameter("login");
            int balance = Integer.parseInt(request.getParameter("balance"));
            String password = Converter.getMD5(request.getParameter("password")); // добавить конвертер в MD5!
            int role = Integer.parseInt(request.getParameter("role"));
            User user = userService.getUserById(id);
            user.setName(name);
            user.setSurname(surname);
            user.setBirthday(birthday);
            user.setPassport(passport);
            user.setAddress(address);
            user.setEmail(email);
            user.setBalance(balance);
            user.setPassword(password);
            user.setRole(roleService.getRoleById(role));
            userService.updateUser(user);
            response.sendRedirect("../cp_employee/success.jsp");
        } catch (Exception ex) {
            logger.error(ex);
            request.getSession().setAttribute("exception", ex);
            response.sendRedirect("../cp_employee/exception.jsp");
        }
    }
}
