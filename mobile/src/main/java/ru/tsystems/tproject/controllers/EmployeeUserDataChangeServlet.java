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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This servlet changes the user's data from the page cp_employee_user_data_change.jsp
 */
public class EmployeeUserDataChangeServlet extends HttpServlet {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final Logger logger = Logger.getLogger(EmployeeUserDataChangeServlet.class);

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImplementation();
        List<Exception> list = new ArrayList<>();
        String name = "";
        String surname = "";
        Date birthday = null;
        String passport = "";
        String address = "";
        String email = "";
        int balance = 0;
        String password = "";
        try {

            int id = Integer.parseInt(request.getParameter("id"));
            if (request.getParameter("name") == null || request.getParameter("name").equals("")) {
                list.add(new Exception("Name can not be null!"));
            } else {
                name = request.getParameter("name");
            }
            if (request.getParameter("surname") == null || request.getParameter("surname").equals("")) {
                list.add(new Exception("Surname can not be null!"));
            } else {
                surname = request.getParameter("surname");
            }
            if (request.getParameter("birthday") != null && !(request.getParameter("birthday").equals(""))) {
                birthday = dateFormat.parse(request.getParameter("birthday"));
            }
            else { list.add(new Exception("Date can not be null!")); }
            if (request.getParameter("passport") != null && !(request.getParameter("passport").equals(""))) {
                passport = request.getParameter("passport");
            }
            else { list.add(new Exception("Passport can not be null!")); }
            if (request.getParameter("address") != null && !(request.getParameter("address").equals(""))) {
                address = request.getParameter("address");
            }
            else { list.add(new Exception("Address can not be null!")); }
            if (request.getParameter("email") != null && !(request.getParameter("email").equals(""))) {
                email = request.getParameter("email");
            }
            else { list.add(new Exception("Email can not be null!")); }

            if (request.getParameter("balance") != null && !(request.getParameter("balance").equals(""))) {
                balance = Integer.parseInt(request.getParameter("balance"));
            }
            else { list.add(new Exception("Balance can not be null!")); }
            if (request.getParameter("password") == null || request.getParameter("password").equals("")) {
                list.add(new Exception("Password can not be null!"));
            } else {
                password = Converter.getMD5(request.getParameter("password"));
            }

            if (list.isEmpty()) {
                request.getSession().removeAttribute("exList");
                request.getSession().setAttribute("userExists", "false");
                User user = userService.getEntityById(id);
                user.setName(name);
                user.setSurname(surname);
                user.setBirthday(birthday);
                user.setPassport(passport);
                user.setAddress(address);
                user.setEmail(email);
                user.setBalance(balance);
                user.setPassword(password);
                userService.updateEntity(user);
                response.sendRedirect("../cp_employee/success.jsp");
            }
            else {
                request.getSession().setAttribute("exList", list);
                request.getSession().setAttribute("userExists", "true");
                response.sendRedirect("../cp_employee/cp_employee_user_data_change.jsp");
            }
        } catch (Exception ex) {
            logger.error(ex);
            request.getSession().setAttribute("exception", ex);
            response.sendRedirect("../cp_employee/exception.jsp");
        }
    }
}
