package ru.tsystems.tproject.controllers;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.Tariff;
import ru.tsystems.tproject.services.API.OptionService;
import ru.tsystems.tproject.services.API.TariffService;
import ru.tsystems.tproject.services.implementation.OptionServiceImplementation;
import ru.tsystems.tproject.services.implementation.TariffServiceImplementation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This servlet is responsible for the changes in the data of the tariff made by employee. It applies the changes done by updating
 * the tariff.
 */
public class TariffDataChangeServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(TariffDataChangeServlet.class);

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TariffService tariffService = new TariffServiceImplementation();
        OptionService optionService = new OptionServiceImplementation();

        try {
            String[] array = request.getParameterValues("cb"); //checkbox of options
            String name = request.getParameter("name");
            int price =  Integer.parseInt(request.getParameter("price"));
            Tariff tariff = tariffService.getTariffById(Integer.parseInt(request.getParameter("id")));
            tariff.removePossibleOptions();
            for (String x : array) {
                int optionId = Integer.parseInt(x);
                tariff.addPossibleOption(optionService.getOptionById(optionId));
            }
            tariffService.updateTariff(tariff);
            response.sendRedirect("../cp_employee/success.html");
        }
        catch (Exception ex) {
            response.sendRedirect("../cp_employee/exception.html");
            logger.error(ex);
        }




    }
}
