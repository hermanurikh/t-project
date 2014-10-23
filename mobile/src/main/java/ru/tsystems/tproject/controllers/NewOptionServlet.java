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
 * This servlet creates a new option with redirecting to success.html or exception.html page afterwards.
 */
public class NewOptionServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(NewOptionServlet.class);
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OptionService optionService = new OptionServiceImplementation();


        try {
            String name = request.getParameter("name");
            int price =  Integer.parseInt(request.getParameter("price"));
            int initialPrice = Integer.parseInt(request.getParameter("initialPrice"));
            Option option = new Option(name, price, initialPrice);
            optionService.createOption(option);
            response.sendRedirect("../cp_employee/success.html");
        }
        catch (Exception ex) {
            response.sendRedirect("../cp_employee/exception.html");
            logger.error(ex);
        }




    }
}
