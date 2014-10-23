package ru.tsystems.tproject.controllers;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.Contract;
import ru.tsystems.tproject.entities.Tariff;
import ru.tsystems.tproject.services.API.ContractService;
import ru.tsystems.tproject.services.API.OptionService;
import ru.tsystems.tproject.services.API.TariffService;
import ru.tsystems.tproject.services.API.UserService;
import ru.tsystems.tproject.services.implementation.ContractServiceImplementation;
import ru.tsystems.tproject.services.implementation.OptionServiceImplementation;
import ru.tsystems.tproject.services.implementation.TariffServiceImplementation;
import ru.tsystems.tproject.services.implementation.UserServiceImplementation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by german on 23.10.14.
 */
public class EmployeeContractCreateServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(EmployeeContractCreateServlet.class);
    /**
     * This method gets an array of option ID's from the request as well as the number of contract. As a result, a contract is created.
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TariffService tariffService = new TariffServiceImplementation();
        OptionService optionService = new OptionServiceImplementation();
        ContractService contractService = new ContractServiceImplementation();
        UserService userService = new UserServiceImplementation();
        long number = 0;
        int userId  = 0;
        int tariffId  = 0;
        int optionId  = 0;
        String[] array;

        try {
            array = request.getParameterValues("cb"); //checkbox of options
            number = Long.parseLong(request.getParameter("number"));
            userId = Integer.parseInt(request.getParameter("userID"));
            tariffId = Integer.parseInt(request.getParameter("tariffID"));
            Contract contract = new Contract(number, userService.getUserById(userId), tariffService.getTariffById(tariffId));
            optionId = 0;
            for (String x : array) {
                optionId = Integer.parseInt(x);
                contract.addOption(optionService.getOptionById(optionId));
            }
            contractService.createContract(contract);
            response.sendRedirect("../cp_employee/success.html");
        }
        catch (Exception ex) {
            logger.error(ex);
            logger.error(number);
            logger.error(userId);
            logger.error(tariffId);
            logger.error(optionId);
            response.sendRedirect("../cp_employee/exception.html");

        }




    }
}
