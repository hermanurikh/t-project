package ru.tsystems.tproject.controllers;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.Tariff;
import ru.tsystems.tproject.services.API.OptionService;
import ru.tsystems.tproject.services.API.TariffService;
import ru.tsystems.tproject.services.implementation.OptionServiceImplementation;
import ru.tsystems.tproject.services.implementation.TariffServiceImplementation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by german on 10/21/14.
 */
public class NewTariffServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(NewTariffServlet.class);
    /**
     * This method gets an array of option ID's from the request as well as the name and the price of the tariff. As a result, a tariff
     * with possible options is created.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TariffService tariffService = new TariffServiceImplementation();
        OptionService optionService = new OptionServiceImplementation();

        try {
            String[] array = request.getParameterValues("cb"); //checkbox of options
            String name = request.getParameter("name");
            int price =  Integer.parseInt(request.getParameter("price"));
            Tariff tariff = new Tariff(name, price);
            for (String x : array) {
                int optionId = Integer.parseInt(x);
                tariff.addPossibleOption(optionService.getOptionById(optionId));
            }
            tariffService.createTariff(tariff);
            response.sendRedirect("../cp_employee/success.html");
        }
        catch (Exception ex) {
            logger.error(ex);
            request.getSession().setAttribute("exception", ex);
            response.sendRedirect("../cp_employee/exception.jsp");

        }




    }
}
