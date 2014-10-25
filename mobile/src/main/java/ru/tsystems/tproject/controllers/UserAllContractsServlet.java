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
import java.util.List;

/**
 * Created by german on 26.10.14.
 */
public class UserAllContractsServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(UserAllContractsServlet.class);
    private ContractService contractService = new ContractServiceImplementation();
    private UserService userService = new UserServiceImplementation();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = (User) request.getSession().getAttribute("currentUserU");
            List<Contract> contractList = user.getContracts();
            request.getSession().setAttribute("contractsList", contractList);
            response.sendRedirect("../cp_client/cp_client_contracts.jsp");
        }
        catch (Exception ex) {
            logger.error(ex);
            request.getSession().setAttribute("exception", ex);
            response.sendRedirect("../cp_client/exception.jsp");
        }


    }
}
