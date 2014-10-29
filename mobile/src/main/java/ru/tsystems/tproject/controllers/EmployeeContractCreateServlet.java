package ru.tsystems.tproject.controllers;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.Contract;
import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.entities.Tariff;
import ru.tsystems.tproject.entities.User;
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
import java.util.ArrayList;
import java.util.List;

/**
 * A class that is intended to create a contract.
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
        String[] array = null;
        Option option;
        List<Option> optionsTogether = new ArrayList<>();
        List<Option> optionsIncompatible = new ArrayList<>();
        List<Option> temporaryList = new ArrayList<>();
        List<Exception> exceptionsList = new ArrayList<>();
        Contract contract = null;
        User user = null;
        try {
            if (request.getParameter("number") == null || request.getParameter("number").equals("")) {
                exceptionsList.add(new Exception("Number can not be null!"));
            }
            else {
                number = Long.parseLong(request.getParameter("number"));
                userId = Integer.parseInt(request.getParameter("userID"));
                user = userService.getUserById(userId);
                tariffId = Integer.parseInt(request.getParameter("tariffID"));
                contract = new Contract(number, user, tariffService.getTariffById(tariffId));

            }

            if (request.getParameterValues("cb") != null && request.getParameterValues("cb").length > 0) {
                array = request.getParameterValues("cb"); //checkbox of options
                if (null != array && array.length > 0) {
                    for (String x : array) {
                        optionId = Integer.parseInt(x);
                        option = optionService.getOptionById(optionId);
                        temporaryList.add(option);
                    }
                }
            }

            if (temporaryList.isEmpty() && exceptionsList.isEmpty()) { // we do not need to check anything if there are no options
                contractService.createContract(contract);
                user.addContract(contract);
                userService.updateUser(user);
                response.sendRedirect("../cp_employee/success.jsp");
            }
            else {
                if (!temporaryList.isEmpty()) {
                    for (Option x : temporaryList) { // for each option
                        optionsTogether = x.getOptionsTogether(); // we get a list of necessary options
                        if (!optionsTogether.isEmpty()) {
                            for (Option necessary : optionsTogether) { //for each option from the together list we check whether it was checked
                                if (!temporaryList.contains(necessary)) { //if it wasn't
                                    exceptionsList.add(new Exception("You didn't select the " + necessary.getName() + " option, but it was necessary for the option " + x.getName()));
                                }
                            }
                        }
                        optionsIncompatible = x.getOptionsIncompatible(); //we get a list of incompatible options
                        if (!optionsIncompatible.isEmpty()) {
                            for (Option incompatible : optionsIncompatible) {
                                if (temporaryList.contains(incompatible)) {
                                    exceptionsList.add(new Exception("You selected the " + incompatible.getName() + " option, but it can't be selected with the option " + x.getName()));
                                }
                            }
                        }
                    }
                    if (exceptionsList.isEmpty()) {
                        for (Option x : temporaryList) {
                            contract.addOption(x);
                        }
                        contractService.createContract(contract);
                        user.addContract(contract);
                        userService.updateUser(user);
                        request.getSession().setAttribute("areExceptions", "false");
                        response.sendRedirect("../cp_employee/success.jsp");
                    }
                    else {
                        request.getSession().setAttribute("areExceptions", "true");
                        request.getSession().setAttribute("exceptionsList", exceptionsList);
                        response.sendRedirect("../cp_employee/cp_employee_new_contract_options.jsp");
                    }
                }

                   else {
                    request.getSession().setAttribute("areExceptions", "true");
                    request.getSession().setAttribute("exceptionsList", exceptionsList);
                    response.sendRedirect("../cp_employee/cp_employee_new_contract_options.jsp");
                }
            }
        }
        catch (Exception ex) {
            logger.error(ex);
            request.getSession().setAttribute("exception", ex);
            response.sendRedirect("../cp_employee/exception.jsp");

        }
    }
}
