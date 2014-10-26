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

/**
 * This servlet creates a new option with redirecting to success.html or exception.jsp page afterwards.
 * It has a check whether the list of options_together and options_incompatible contain the same object.
 */
public class EmployeeFinalOptionChangeServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(EmployeeFinalOptionChangeServlet.class);
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OptionService optionService = new OptionServiceImplementation();
        try {
            String name = request.getParameter("name");
            int price =  Integer.parseInt(request.getParameter("price"));
            int initialPrice = Integer.parseInt(request.getParameter("initialPrice"));
            Option option = (Option) request.getSession().getAttribute("option");
            option.setName(name);
            option.setPrice(price);
            option.setInitialPrice(initialPrice);
            option.removeOptionsIncompatible();
            option.removeOptionsTogether();

            String[] optionsTogether = null;
            String[] optionsIncompatible = null;
            if (request.getParameterValues("cb") != null && request.getParameterValues("cb").length > 0) {
                optionsTogether = request.getParameterValues("cb");
                if (null != optionsTogether && optionsTogether.length > 0) {
                    for (String optionTogether : optionsTogether) {
                        int optionId = Integer.parseInt(optionTogether);
                        option.addOptionsTogether(optionService.getOptionById(optionId));
                    }
                }
            }
            if (request.getParameterValues("cb2") != null && request.getParameterValues("cb2").length > 0) {
                optionsIncompatible = request.getParameterValues("cb2");
                if (null != optionsIncompatible && optionsIncompatible.length > 0) {
                    for (String optionIncompatible : optionsIncompatible) {
                        int optionId = Integer.parseInt(optionIncompatible);
                        option.addOptionsIncompatible(optionService.getOptionById(optionId));
                    }
                }
            }

            for (Option x : option.getOptionsTogether()) { // a check for the same option in two lists
                if (option.getOptionsIncompatible().contains(x)) {
                    throw new Exception("The options were incompatible!");
                }
            }
            for (Option x : option.getOptionsIncompatible()) { // a check for the same option in two lists
                if (option.getOptionsTogether().contains(x)) {
                    throw new Exception("The options were incompatible!");
                }
            }

            optionService.updateOption(option);
            response.sendRedirect("../cp_employee/success.html");
        }
        catch (Exception ex) {
            logger.error(ex);
            request.getSession().setAttribute("exception", ex);
            response.sendRedirect("../cp_employee/exception.jsp");
        }




    }
}