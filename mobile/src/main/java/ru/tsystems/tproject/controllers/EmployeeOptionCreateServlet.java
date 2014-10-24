package ru.tsystems.tproject.controllers;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.services.API.OptionService;
import ru.tsystems.tproject.services.implementation.OptionServiceImplementation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * This servlet is responsible for giving the info to the page where a new option is created. It sets a list of options to be checked.
 */
public class EmployeeOptionCreateServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(EmployeeOptionCreateServlet.class);

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        OptionService optionService = new OptionServiceImplementation();
        response.setContentType("text/html;charset=utf-8");
        try {
            List<Option> optionsList = optionService.getAllOptions();
            request.getSession().setAttribute("optionsList", optionsList);
            response.sendRedirect("../cp_employee/cp_employee_new_option.jsp");
        }
        catch (Exception ex) {
            logger.error(ex);
            request.getSession().setAttribute("exception", ex);
            response.sendRedirect("../cp_employee/exception.jsp");
        }
    }
}
