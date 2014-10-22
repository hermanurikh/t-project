package ru.tsystems.tproject.controllers;

import ru.tsystems.tproject.entities.Tariff;
import ru.tsystems.tproject.services.API.TariffService;
import ru.tsystems.tproject.services.implementation.TariffServiceImplementation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by german on 10/21/14.
 */
public class NewTariffServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TariffService tariffService = new TariffServiceImplementation();

        try {
            String name = request.getParameter("name");
            int price =  Integer.parseInt(request.getParameter("price"));
            tariffService.createTariff(new Tariff(name, price));
            response.sendRedirect("../cp_employee/success.html");
        }
        catch (Exception ex) {
            response.sendRedirect("../cp_employee/exception.html");
        }




    }
}
