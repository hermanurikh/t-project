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
 * This is a servlet that shows all tariffs on the cp_employee_new_contract.jsp page.
 *
 */
public class EmployeeContractServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(EmployeeContractServlet.class);


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=utf-8");
        try {
            TariffService tariffService = new TariffServiceImplementation();
            List<Tariff> tariffsList = tariffService.getAllTariffs();
            request.getSession().setAttribute("tariffsList", tariffsList);
            /*RequestDispatcher rd = request.getRequestDispatcher("../cp_employee/cp_employee_options.jsp");
            rd.forward(request, response);*/
            response.sendRedirect("../cp_employee/cp_employee_new_contract.jsp");
        }
        catch (Exception ex) {
            logger.error(ex);
            response.sendRedirect("../cp_employee/exception.html");

        }
    }
}