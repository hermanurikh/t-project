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
 * This servlet is responsible for giving the info to the page where an option is changed. It gives four lists of options:
 * options together, which will be already checked;
 * other options in the section together, unchecked;
 * options incompatible, which will be already checked;
 * other options in the section incompatible, unchecked.
 *
 */
public class EmployeeOptionChangeServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(EmployeeOptionChangeServlet.class);

    public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        OptionService optionService = new OptionServiceImplementation();
        response.setContentType("text/html;charset=utf-8");
        try {
            int optionId = Integer.parseInt(request.getParameter("optionId"));
            Option option = optionService.getOptionById(optionId);
            List<Option> optionsListAllTogether = optionService.getAllOptions();
            List<Option> optionsTogether = option.getOptionsTogether(); // a list of options together, checked
            optionsListAllTogether.removeAll(optionsTogether); // a list of other options together, unchecked
            optionsListAllTogether.remove(option);
            List<Option> optionsListAllIncompatible = optionService.getAllOptions();
            List<Option> optionsIncompatible = option.getOptionsIncompatible(); // a list of incompatible options, checked
            optionsListAllIncompatible.removeAll(optionsIncompatible); // and unchecked
            optionsListAllIncompatible.remove(option);
            request.getSession().setAttribute("optionsTogether", optionsTogether);
            request.getSession().setAttribute("optionsListAllTogether", optionsListAllTogether);
            request.getSession().setAttribute("optionsIncompatible", optionsIncompatible);
            request.getSession().setAttribute("optionsListAllIncompatible", optionsListAllIncompatible);
            request.getSession().setAttribute("option", option);
            response.sendRedirect("../cp_employee/cp_employee_change_option.jsp");
        }
        catch (Exception ex) {
            logger.error(ex);
            request.getSession().setAttribute("exception", ex);
            response.sendRedirect("../cp_employee/exception.jsp");
        }
    }
}
