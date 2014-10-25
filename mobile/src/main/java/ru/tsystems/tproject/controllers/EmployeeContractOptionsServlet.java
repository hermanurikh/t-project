package ru.tsystems.tproject.controllers;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.entities.Tariff;
import ru.tsystems.tproject.services.API.TariffService;
import ru.tsystems.tproject.services.API.UserService;
import ru.tsystems.tproject.services.implementation.TariffServiceImplementation;
import ru.tsystems.tproject.services.implementation.UserServiceImplementation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * This servlet receives the info about the tariff selected for the contract and gives back the possible options to choose from.
 * We get here an array of options id's to pass it to javascript on the page.
 */

public class EmployeeContractOptionsServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(EmployeeContractOptionsServlet.class);
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImplementation();
        TariffService tariffService = new TariffServiceImplementation();
        try {
            String contractNumber = request.getParameter("number");
            String login = request.getParameter("login");
            if (login == null) login = String.valueOf(request.getSession().getAttribute("login"));
            if (contractNumber == null) contractNumber = String.valueOf(request.getSession().getAttribute("login"));
            int userID = userService.getUserByLogin(login).getId();
            int tariffID = Integer.parseInt(request.getParameter("cb"));
            Tariff tariff = tariffService.getTariffById(tariffID);
            List<Option> optionsList = tariff.getPossibleOptions();
            request.getSession().setAttribute("optionsList", optionsList);
            request.getSession().setAttribute("contractNumber", contractNumber);
            request.getSession().setAttribute("userId", userID);
            request.getSession().setAttribute("tariffId", tariffID);
            response.sendRedirect("../cp_employee/cp_employee_new_contract_options.jsp");


        }
        catch (Exception ex) {
            logger.error(ex);
            request.getSession().setAttribute("exception", ex);
            response.sendRedirect("../cp_employee/exception.jsp");
        }

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImplementation();
        TariffService tariffService = new TariffServiceImplementation();
        try {
            String contractNumber = request.getParameter("number");
            String login = request.getParameter("login");
            if (login == null) login = String.valueOf(request.getSession().getAttribute("login"));
            if (contractNumber == null) contractNumber = String.valueOf(request.getSession().getAttribute("number"));
            int userID = userService.getUserByLogin(login).getId();
            int tariffID = Integer.parseInt(request.getParameter("cb"));
            Tariff tariff = tariffService.getTariffById(tariffID);
            List<Option> optionsList = tariff.getPossibleOptions();
            request.getSession().setAttribute("optionsList", optionsList);
            request.getSession().setAttribute("contractNumber", contractNumber);
            request.getSession().setAttribute("userId", userID);
            request.getSession().setAttribute("tariffId", tariffID);
            response.sendRedirect("../cp_employee/cp_employee_contract_change_options.jsp");


        }
        catch (Exception ex) {
            logger.error(ex);
            request.getSession().setAttribute("exception", ex);
            response.sendRedirect("../cp_employee/exception.jsp");
        }

    }
}
