package ru.tsystems.tproject.controllers;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.Contract;
import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.services.API.ContractService;
import ru.tsystems.tproject.services.API.OptionService;
import ru.tsystems.tproject.services.API.TariffService;
import ru.tsystems.tproject.services.implementation.ContractServiceImplementation;
import ru.tsystems.tproject.services.implementation.OptionServiceImplementation;
import ru.tsystems.tproject.services.implementation.TariffServiceImplementation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by german on 25.10.14.
 */
public class UserContractFinalChangeServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UserContractFinalChangeServlet.class);
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
        long number;
        int tariffId;
        int optionId;
        String[] array;
        Option option;
        List<Option> optionsTogether;
        List<Option> optionsIncompatible;
        List<Option> temporaryList = new ArrayList<>();
        List<Exception> exceptionsList = new ArrayList<>();

        try {
            number = Long.parseLong(String.valueOf(request.getSession().getAttribute("number")));
            tariffId = Integer.parseInt(String.valueOf(request.getSession().getAttribute("tariffId")));
            Contract contract = contractService.getContractByNumber(number);
            if (contract.isBlocked()) {
                response.sendRedirect("../cp_client/cp_client_change_contract.jsp"); // если заблокирован, нельзя
            }
            else {
                contract.setTariff(tariffService.getEntityById(tariffId));
                optionId = 0;
                if (request.getParameterValues("cb") != null && request.getParameterValues("cb").length > 0) {
                    array = request.getParameterValues("cb"); //checkbox of options
                    if (null != array && array.length > 0) {
                        for (String x : array) {
                            optionId = Integer.parseInt(x);
                            option = optionService.getEntityById(optionId);
                            temporaryList.add(option);
                        }
                    }
                }

                if (temporaryList.isEmpty()) { // we do not need to check anything if there are no options
                    contract.removeAllOptions();
                    request.getSession().setAttribute("updatedContract", contract);
                    request.getSession().setAttribute("optionsList", contract.getOptions());
                    request.getSession().setAttribute("areExceptions", "false");
                    response.sendRedirect("../cp_client/cp_client_contract_change_bucket.jsp");
                } else {
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
                        contract.removeAllOptions();
                        for (Option x : temporaryList) {
                            contract.addOption(x);
                        }
                        request.getSession().setAttribute("updatedContract", contract);
                        request.getSession().setAttribute("optionsList", contract.getOptions());
                        request.getSession().setAttribute("areExceptions", "false");
                        response.sendRedirect("../cp_client/cp_client_contract_change_bucket.jsp");
                    } else {
                        request.getSession().setAttribute("areExceptions", "true");
                        request.getSession().setAttribute("exceptionsList", exceptionsList);
                        response.sendRedirect("../cp_client/cp_client_contract_change_options.jsp");
                    }
                }
            }
        }
        catch (Exception ex) {
            logger.error(ex);
            request.getSession().setAttribute("exception", ex);
            response.sendRedirect("../cp_client/exception.jsp");

        }




    }
}
