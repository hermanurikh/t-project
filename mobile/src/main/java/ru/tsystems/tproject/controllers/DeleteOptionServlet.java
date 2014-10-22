package ru.tsystems.tproject.controllers;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.Option;
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
import java.util.List;

/**
 * This servlet deletes an option:
 * at first it is deleted from the list of possible options of each tariff;
 * then it is deleted from the database.
 */
public class DeleteOptionServlet extends HttpServlet {
    private static List<Option> optionsList;
    private static Logger logger = Logger.getLogger(DeleteOptionServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=utf-8");
        try {
            OptionService optionService = new OptionServiceImplementation();
            TariffService tariffService = new TariffServiceImplementation();
            int optionID = Integer.parseInt(request.getParameter("optionId"));
            Option option = optionService.getOptionById(optionID);
            List<Tariff> tariffs = tariffService.getAllTariffs();
            for (Tariff x : tariffs) {
                x.removeOptionForTariff(option);
            }
            optionService.deleteOption(option);
            optionsList = optionService.getAllOptions();
            request.getSession().setAttribute("optionsList", optionsList);
            response.sendRedirect("../cp_employee/cp_employee_options.jsp");
        }
        catch (Exception ex) {
            response.sendRedirect("../cp_employee/exception.html");
            logger.error(ex);
        }
    }
}
