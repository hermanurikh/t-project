package ru.tsystems.tproject.controllers;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.entities.Tariff;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.exceptions.CustomDAOException;
import ru.tsystems.tproject.services.API.TariffService;
import ru.tsystems.tproject.services.API.UserService;
import ru.tsystems.tproject.services.implementation.TariffServiceImplementation;
import ru.tsystems.tproject.services.implementation.UserServiceImplementation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This servlet receives the info about the tariff selected for the contract and gives back the possible options to choose from.
 * It has two methods in order to work with the creation of the contract as well as the updating.
 */

public class EmployeeContractOptionsServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(EmployeeContractOptionsServlet.class);
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImplementation();
        TariffService tariffService = new TariffServiceImplementation();
        List<Exception> list = new ArrayList<>();
        String contractNumber = "";
        try {
            User user = null;
            if (request.getParameter("login") == null || request.getParameter("login").equals("")) {
                list.add(new Exception("Login can't be null!"));
            }
            if (request.getParameter("cb") == null) {
                list.add(new Exception("Please choose a contract!"));
            }
            if (request.getParameter("number") == null || request.getParameter("number").equals("")) {
                list.add(new Exception("Contract number can't be null!"));
            }
            else {
                contractNumber = request.getParameter("number");
                try {
                    user = userService.getUserByNumber(Long.parseLong(contractNumber));
                }
                catch (CustomDAOException ex) {
                    logger.error("User not found, everything correct");
                }
            }
            String login = request.getParameter("login");
            try { userService.getUserByLogin(login); }
            catch (CustomDAOException ex) {
                list.add(new Exception("User with login " + login + " not found!"));
            }
             if (user != null) {
                 list.add(new Exception("A contract with this number already exists!"));

             }
                if (!list.isEmpty()) {
                    request.getSession().setAttribute("exList", list);
                    request.getSession().setAttribute("userExists", "true");
                    response.sendRedirect("../cp_employee/cp_employee_new_contract.jsp");
                }
             else {
                    request.getSession().setAttribute("userExists", "false");

                    if (contractNumber == null) {
                        contractNumber = String.valueOf(request.getSession().getAttribute("number"));
                    }
                    int userID = userService.getUserByLogin(login).getId();
                    int tariffID = Integer.parseInt(request.getParameter("cb"));
                    Tariff tariff = tariffService.getEntityById(tariffID);
                    List<Option> optionsList = tariff.getPossibleOptions();
                    request.getSession().setAttribute("optionsList", optionsList);
                    request.getSession().setAttribute("contractNumber", contractNumber);
                    request.getSession().setAttribute("userId", userID);
                    request.getSession().setAttribute("tariffId", tariffID);
                    request.getSession().setAttribute("tariff", tariff);
                    response.sendRedirect("../cp_employee/cp_employee_new_contract_options.jsp");
                }



        }
        catch (Exception ex) {
            logger.error(ex);
            request.getSession().setAttribute("userExists", "true");
            request.getSession().setAttribute("exception", ex);
            response.sendRedirect("../cp_employee/cp_employee_new_contract.jsp");
        }

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserServiceImplementation();
        TariffService tariffService = new TariffServiceImplementation();
        try {
            List<Exception> list = new ArrayList<>();
            String contractNumber = request.getParameter("number");
            String login = request.getParameter("login");
            if (login == null) login = String.valueOf(request.getSession().getAttribute("login"));
            if (contractNumber == null) contractNumber = String.valueOf(request.getSession().getAttribute("number"));
            if (request.getParameter("cb") == null) {
                list.add(new Exception("Please choose a tariff"));
                request.getSession().setAttribute("exList", list);
                request.getSession().setAttribute("userExists", "true");
                response.sendRedirect("../cp_employee/cp_employee_change_contract.jsp");
            }
            else {
                int userID = userService.getUserByLogin(login).getId();
                int tariffID = Integer.parseInt(request.getParameter("cb"));
                Tariff tariff = tariffService.getEntityById(tariffID);
                List<Option> optionsList = tariff.getPossibleOptions();
                request.getSession().setAttribute("optionsList", optionsList);
                request.getSession().setAttribute("contractNumber", contractNumber);
                request.getSession().setAttribute("userId", userID);
                request.getSession().setAttribute("tariffId", tariffID);
                request.getSession().setAttribute("tariff", tariff);
                request.getSession().setAttribute("userExists", "false");
                response.sendRedirect("../cp_employee/cp_employee_contract_change_options.jsp");
            }
        }
        catch (Exception ex) {
            logger.error(ex);
            request.getSession().setAttribute("exception", ex);
            response.sendRedirect("../cp_employee/exception.jsp");
        }

    }
}
