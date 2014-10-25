package ru.tsystems.tproject.controllers;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.Contract;
import ru.tsystems.tproject.entities.Option;
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
import java.util.List;

/**
 * Created by german on 23.10.14.
 */
public class EmployeeContractCreateServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(EmployeeContractCreateServlet.class);
    /**
     * This method gets an array of option ID's from the request as well as the number of contract. As a result, a contract is created.
     * It checks whether the options' choice is correct.
     * We make a temporary list which holds all the selected options, then we check whether it doesn't have conflicts.
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
        Option option;
        List<Option> optionsTogether;
        List<Option> optionsIncompatible;
        List<Option> temporaryList = null;

        try {
            array = request.getParameterValues("cb"); //checkbox of options
            number = Long.parseLong(request.getParameter("number"));
            userId = Integer.parseInt(request.getParameter("userID"));
            tariffId = Integer.parseInt(request.getParameter("tariffID"));
            Contract contract = new Contract(number, userService.getUserById(userId), tariffService.getTariffById(tariffId));
            optionId = 0;
            for (String x : array) {
                optionId = Integer.parseInt(x);
                option = optionService.getOptionById(optionId);
                temporaryList.add(option);
            }
            if (temporaryList == null) { // we do not need to check anything if there are no options
                contractService.createContract(contract);
                response.sendRedirect("../cp_employee/success.html");
            }
            else {
                
            }
        }
        catch (Exception ex) {
            logger.error(ex);
            request.getSession().setAttribute("exception", ex);
            response.sendRedirect("../cp_employee/exception.jsp");

        }




    }
}
