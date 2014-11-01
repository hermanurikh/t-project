package ru.tsystems.tproject.controllers;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.Contract;
import ru.tsystems.tproject.entities.Tariff;
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
 * This servlet is responsible for giving out the cp_client_change_contract.jsp page.
 */
public class UserContractChangeServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UserContractChangeServlet.class);


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        ContractService contractService = new ContractServiceImplementation();
        response.setContentType("text/html;charset=utf-8");
        try {
            Contract contract = contractService.getContractById(Integer.parseInt(request.getParameter("contractId")));
            request.getSession().setAttribute("number", contract.getNumber());
            if (contract.isBlocked()) {
                if (contract.getEmployee() != null) {
                    request.getSession().setAttribute("paramIsBlocked", "ВКЛЮЧЕНА АДМИНИСТРАТОРОМ. Вы не можете самостоятельно разблокировать контракт. Пожалуйста, обратитесь к администратору");
                }
                else {
                    request.getSession().setAttribute("paramIsBlocked", "ВКЛЮЧЕНА");
                }
            }
            else {
                request.getSession().setAttribute("paramIsBlocked", "выключена");
            }
            TariffService tariffService = new TariffServiceImplementation();
            List<Tariff> tariffsList = tariffService.getAllTariffs();
            request.getSession().setAttribute("tariffsList", tariffsList);
            response.sendRedirect("../cp_client/cp_client_change_contract.jsp");
        }
        catch (Exception ex) {
            logger.error(ex);
            request.getSession().setAttribute("exception", ex);
            response.sendRedirect("../cp_employee/exception.jsp");

        }
    }
}
