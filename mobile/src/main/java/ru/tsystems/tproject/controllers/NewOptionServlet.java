package ru.tsystems.tproject.controllers;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.services.API.OptionService;
import ru.tsystems.tproject.services.implementation.OptionServiceImplementation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This servlet creates a new option with redirecting to success.html or exception.jsp page afterwards.
 * It has a check whether the list of options_together and options_incompatible contain the same object.
 */
public class NewOptionServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(NewOptionServlet.class);
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OptionService optionService = new OptionServiceImplementation();
        try {
            String name = request.getParameter("name");
            int price =  Integer.parseInt(request.getParameter("price"));
            int initialPrice = Integer.parseInt(request.getParameter("initialPrice"));
            String[] optionsTogether = request.getParameterValues("cb");
            String[] optionsIncompatible = request.getParameterValues("cb2");
            Option option = new Option(name, price, initialPrice);
            for (String optionTogether : optionsTogether) {
                int optionId = Integer.parseInt(optionTogether);
                option.addOptionsTogether(optionService.getOptionById(optionId));
            }
            for (String optionIncompatible : optionsIncompatible) {
                int optionId = Integer.parseInt(optionIncompatible);
                option.addOptionsIncompatible(optionService.getOptionById(optionId));
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

            optionService.createOption(option);
            response.sendRedirect("../cp_employee/success.html");
        }
        catch (Exception ex) {
            logger.error(ex);
            request.getSession().setAttribute("exception", ex);
            response.sendRedirect("../cp_employee/exception.jsp");
        }




    }
}
