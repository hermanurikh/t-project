package ru.tsystems.tproject.controllers;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.Contract;
import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.services.API.ContractService;
import ru.tsystems.tproject.services.implementation.ContractServiceImplementation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by german on 26.10.14.
 */
public class UserContractDetailServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UserContractDetailServlet.class);


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        ContractService contractService = new ContractServiceImplementation();
        response.setContentType("text/html;charset=utf-8");
        try {
            Contract contract = contractService.getEntityById(Integer.parseInt(request.getParameter("contractId")));
            request.getSession().setAttribute("contract", contract);
            List<Option> optionList = contract.getOptions();
            int amount = contract.getTariff().getPrice();
            if (!optionList.isEmpty()) {
                for (Option x : optionList) {
                    amount += x.getPrice();
                }
            }
            request.getSession().setAttribute("optionsList", optionList);
            request.getSession().setAttribute("totalAmount", amount);
            if (contract.isBlocked()) {
                if (contract.getEmployee() != null) {
                    request.getSession().setAttribute("isItBlocked", "ЗАБЛОКИРОВАН АДМИНИСТРАТОРОМ");
                }
                else {
                    request.getSession().setAttribute("isItBlocked", "ЗАБЛОКИРОВАН");
                }
            }
            else {
                request.getSession().setAttribute("isItBlocked", "активен");
            }
            response.sendRedirect("../cp_client/cp_client_contract_details.jsp");


        }
        catch (Exception x) {
            logger.error(x);
            response.sendRedirect("../cp_client/exception.jsp");

        }
    }
}
