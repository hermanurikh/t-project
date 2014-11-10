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
 * This servlet is a servlet that shows all the tariffs on cp_employee_tariffs.jsp page.
 */
public class TariffsServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(TariffsServlet.class);


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=utf-8");
        try {
            TariffService tariffService = new TariffServiceImplementation();
            List<Tariff> tariffsList = tariffService.getAll();
            request.getSession().setAttribute("tariffsList", tariffsList);
            /*RequestDispatcher rd = request.getRequestDispatcher("../cp_employee/cp_employee_options.jsp");
            rd.forward(request, response);*/
            response.sendRedirect("../cp_employee/cp_employee_tariffs.jsp");
        }
        catch (Exception ex) {
            logger.error(ex);
            request.getSession().setAttribute("exception", ex);
            response.sendRedirect("../cp_employee/exception.jsp");
        }
    }
}