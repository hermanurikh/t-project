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
public class EmployeeBlockServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(EmployeeBlockServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        ContractService contractService = new ContractServiceImplementation();
        try {
            long number = Long.parseLong(request.getParameter("contractNumber"));
            Contract contract = contractService.getContractByNumber(number);
            if (contract.isBlocked()) {
                contract.setBlocked(false);
                contract.setEmployee(null);
                contractService.updateEntity(contract);
                request.getSession().setAttribute("paramIsBlocked", "выключена");
            }
            else {
                contract.setBlocked(true);
                contract.setEmployee((User)request.getSession().getAttribute("currentUserU"));
                contractService.updateEntity(contract);
                request.getSession().setAttribute("paramIsBlocked", "ВКЛЮЧЕНА");
            }
            response.sendRedirect("../cp_employee/cp_employee_change_contract.jsp");


        }
        catch (Exception ex) {
                logger.error(ex);
                request.getSession().setAttribute("exception", ex);
                response.sendRedirect("../cp_employee/exception.jsp");


        }
    }
}
