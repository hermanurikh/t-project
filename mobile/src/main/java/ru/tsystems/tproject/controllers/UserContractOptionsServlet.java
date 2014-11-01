package ru.tsystems.tproject.controllers;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.entities.Tariff;
import ru.tsystems.tproject.entities.User;
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
 * This servlet receives the info about the tariff selected for the contract and gives back the possible options to choose from.
 * It gives out the page cp_client_contract_change_options.jsp
 */

public class UserContractOptionsServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UserContractOptionsServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ContractService contractService = new ContractServiceImplementation();
        TariffService tariffService = new TariffServiceImplementation();
        try {
            String contractNumber = request.getParameter("number");
            if (contractNumber == null) contractNumber = String.valueOf(request.getSession().getAttribute("number"));
            if ((contractService.getContractByNumber(Long.parseLong(String.valueOf(request.getSession().getAttribute("number"))))).isBlocked()) {
                response.sendRedirect("../cp_client/cp_client_change_contract.jsp"); // если заблокирован, нельзя
            }
            else {
                int userID = ((User) (request.getSession().getAttribute("currentUserU"))).getId();
                if (request.getParameter("cb") != null) {
                    int tariffID = Integer.parseInt(request.getParameter("cb"));
                    Tariff tariff = tariffService.getTariffById(tariffID);
                    List<Option> optionsList = tariff.getPossibleOptions();
                    request.getSession().setAttribute("optionsList", optionsList);
                    request.getSession().setAttribute("contractNumber", contractNumber);
                    request.getSession().setAttribute("userId", userID);
                    request.getSession().setAttribute("tariffId", tariffID);
                    request.getSession().setAttribute("tariff", tariff);
                    request.getSession().setAttribute("userExists", "false");
                    request.getSession().setAttribute("areExceptions", "false");
                    response.sendRedirect("../cp_client/cp_client_contract_change_options.jsp");
                }
                else {
                    request.getSession().setAttribute("areExceptions", "true");
                    response.sendRedirect("../cp_client/cp_client_change_contract.jsp");
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
