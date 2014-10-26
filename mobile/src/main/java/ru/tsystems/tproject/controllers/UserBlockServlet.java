package ru.tsystems.tproject.controllers;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.Contract;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.services.API.ContractService;
import ru.tsystems.tproject.services.implementation.ContractServiceImplementation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This servlet blocks/unblocks the client.
 */
public class UserBlockServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(UserBlockServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        ContractService contractService = new ContractServiceImplementation();
        try {
            long number = Long.parseLong(request.getParameter("contractNumber"));
            Contract contract = contractService.getContractByNumber(number);
            if (contract.isBlocked()) {
                contract.setBlocked(false);
                contractService.updateContract(contract);
                request.getSession().setAttribute("paramIsBlocked", "выключена");
            }
            else {
                contract.setBlocked(true);
                contractService.updateContract(contract);
                request.getSession().setAttribute("paramIsBlocked", "ВКЛЮЧЕНА. Вы не можете произвести изменения с контрактом");
            }
            response.sendRedirect("../cp_client/cp_client_change_contract.jsp");


        }
        catch (Exception ex) {
                logger.error(ex);
                request.getSession().setAttribute("exception", ex);
                response.sendRedirect("../cp_client/exception.jsp");


        }
    }
}
