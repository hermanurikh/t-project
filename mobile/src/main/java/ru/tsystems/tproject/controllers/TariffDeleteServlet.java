package ru.tsystems.tproject.controllers;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.Contract;
import ru.tsystems.tproject.entities.Tariff;
import ru.tsystems.tproject.services.API.ContractService;
import ru.tsystems.tproject.services.API.TariffService;
import ru.tsystems.tproject.services.implementation.ContractServiceImplementation;
import ru.tsystems.tproject.services.implementation.TariffServiceImplementation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * This servlet deletes a tariff and redirects to the page of all tariffs. If some contract has this tariff, it is switched
 * to base tariff, and 500 roubles are compensated.
 */
public class TariffDeleteServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(TariffDeleteServlet.class);


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=utf-8");
        try {
            TariffService tariffService = new TariffServiceImplementation();
            ContractService contractService = new ContractServiceImplementation();
            int tariffId = Integer.parseInt(request.getParameter("id"));
            int balance;
            Tariff tariff = tariffService.getTariffById(tariffId);
            List<Contract> contractList = contractService.getAllContracts();
            for (Contract x : contractList) {
                if (x.getTariff() == tariff) {
                    x.setTariff(tariffService.getTariffById(11));
                    balance = x.getUser().getBalance() + 500;
                    x.getUser().setBalance(balance);
                }
            }
            tariffService.deleteTariff(tariff);
            List<Tariff> tariffsList = tariffService.getAllTariffs();
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
