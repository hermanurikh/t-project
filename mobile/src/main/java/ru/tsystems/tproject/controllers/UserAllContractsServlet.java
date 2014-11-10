package ru.tsystems.tproject.controllers;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.Contract;
import ru.tsystems.tproject.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * This servlet gives out all users' contracts.
 */
public class UserAllContractsServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UserAllContractsServlet.class);


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = (User) request.getSession().getAttribute("currentUserU");
            //User newUser = userService.getEntityById(user.getId());
            List<Contract> contractList = user.getContracts();
            logger.error(contractList);
            request.getSession().setAttribute("contractsList", null);
            request.getSession().setAttribute("contractsUserList", null);
            request.getSession().setAttribute("contractsUserList", contractList);
            response.sendRedirect("../cp_client/cp_client_contracts.jsp");
        }
        catch (Exception ex) {
            logger.error(ex);
            request.getSession().setAttribute("exception", ex);
            response.sendRedirect("../cp_client/exception.jsp");
        }


    }
}
