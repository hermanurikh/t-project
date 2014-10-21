package ru.tsystems.tproject.controllers;

import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.exceptions.CustomDAOException;
import ru.tsystems.tproject.services.API.OptionService;
import ru.tsystems.tproject.services.implementation.OptionServiceImplementation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by german on 21.10.14.
 */
public class NewOptionServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OptionService optionService = new OptionServiceImplementation();
        RequestDispatcher rd = null;

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
        }




    }
}
