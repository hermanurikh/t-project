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

/**
 * Created by german on 26.10.14.
 */
public class UserBucketApprovedServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UserBucketApprovedServlet.class);
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ContractService contractService = new ContractServiceImplementation();
        try {
            Contract contract = (Contract) req.getSession().getAttribute("updatedContract");
            contractService.updateContract(contract);
            resp.sendRedirect("../cp_client/success.jsp");
        }
        catch (Exception ex) {
            logger.error(ex);
            req.getSession().setAttribute("exception", ex);
            resp.sendRedirect("../cp_client/exception.jsp");
        }

    }
}
