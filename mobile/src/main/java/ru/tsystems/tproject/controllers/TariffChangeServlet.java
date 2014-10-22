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
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



/**
 * This servlet is responsible for the info got by cp_employee_change_tariff.jsp, it shows the current info about the tariff.
 * It has two lists = a list of current options for tariff and a list of all options. The options which are in the first list are deleted
 * from the second.
 */
public class TariffChangeServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(TariffChangeServlet.class);
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        OptionService optionService = new OptionServiceImplementation();
        TariffService tariffService = new TariffServiceImplementation();
        response.setContentType("text/html;charset=utf-8");
        try {
            int tariffId = Integer.parseInt(request.getParameter("id"));
            Tariff tariff = tariffService.getTariffById(tariffId);
            List<Option> allOptionsList = optionService.getAllOptions();
            List<Option> currentTariffOptionsList = optionService.getAllOptionsForTariff(tariffId);
            allOptionsList.removeAll(currentTariffOptionsList);
            request.getSession().setAttribute("currentOptionsList", currentTariffOptionsList);
            request.getSession().setAttribute("allOptionsList", allOptionsList);
            request.getSession().setAttribute("name", tariff.getName());
            request.getSession().setAttribute("price", tariff.getPrice());
            request.getSession().setAttribute("id", tariff.getId());
            /*RequestDispatcher rd = request.getRequestDispatcher("../cp_employee/cp_employee_options.jsp");
            rd.forward(request, response);*/
            response.sendRedirect("../cp_employee/cp_employee_change_tariff.jsp");
        }
        catch (Exception ex) {
            response.sendRedirect("../cp_employee/exception.html");
            logger.error(ex);

        }
    }
}
