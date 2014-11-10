package ru.tsystems.tproject.controllers;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.Contract;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.services.API.ContractService;
import ru.tsystems.tproject.services.API.UserService;
import ru.tsystems.tproject.services.implementation.ContractServiceImplementation;
import ru.tsystems.tproject.services.implementation.UserServiceImplementation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by german on 25.10.14.
 */
public class EmployeeContractDeleteServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(EmployeeContractDeleteServlet.class);


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        ContractService contractService = new ContractServiceImplementation();
        UserService userService = new UserServiceImplementation();
        response.setContentType("text/html;charset=utf-8");
        try {
            Contract contract = contractService.getEntityById(Integer.parseInt(request.getParameter("contractId")));
            User user = userService.getUserByNumber(contract.getNumber());
            contractService.deleteEntity(contract);
            user.removeContract(contract);
            userService.updateEntity(user);
            request.getSession().setAttribute("contractsList", contractService.getAll());
            response.sendRedirect("../cp_employee/cp_employee_contracts.jsp");
        }
        catch (Exception ex) {
            logger.error(ex);
            request.getSession().setAttribute("exception", ex);
            response.sendRedirect("../cp_employee/exception.jsp");

        }
    }
}
