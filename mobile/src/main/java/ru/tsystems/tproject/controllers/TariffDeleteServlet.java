package ru.tsystems.tproject.controllers;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.Tariff;
import ru.tsystems.tproject.services.API.TariffService;
import ru.tsystems.tproject.services.implementation.TariffServiceImplementation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * This servlet deletes a tariff and redirects to the page of all tariffs.
 */
public class TariffDeleteServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(TariffsServlet.class);


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=utf-8");
        try {
            TariffService tariffService = new TariffServiceImplementation();
            int tariffId = Integer.parseInt(request.getParameter("id"));
            Tariff tariff = tariffService.getTariffById(tariffId);
            tariffService.deleteTariff(tariff);
            List<Tariff> tariffsList = tariffService.getAllTariffs();
            request.getSession().setAttribute("tariffsList", tariffsList);
            /*RequestDispatcher rd = request.getRequestDispatcher("../cp_employee/cp_employee_options.jsp");
            rd.forward(request, response);*/
            response.sendRedirect("../cp_employee/cp_employee_tariffs.jsp");
        }
        catch (Exception ex) {
            response.sendRedirect("../cp_employee/exception.html");
            logger.error(ex);
        }
    }
}
