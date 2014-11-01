package ru.tsystems.tproject.controllers;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.Contract;
import ru.tsystems.tproject.services.API.ContractService;
import ru.tsystems.tproject.services.implementation.ContractServiceImplementation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * This servlet shows all contracts on the cp_employee_contracts.jsp page
 */
public class EmployeeAllContractsServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(EmployeeAllContractsServlet.class);
    private final ContractService contractService = new ContractServiceImplementation();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Contract> contractList = contractService.getAllContracts();
            request.getSession().setAttribute("contractsList", contractList);
            response.sendRedirect("../cp_employee/cp_employee_contracts.jsp");
        }
        catch (Exception ex) {
            logger.error(ex);
            request.getSession().setAttribute("exception", ex);
            response.sendRedirect("../cp_employee/exception.jsp");
        }


    }
}
