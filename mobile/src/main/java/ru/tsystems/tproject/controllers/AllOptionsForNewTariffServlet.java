package ru.tsystems.tproject.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.services.API.OptionService;
import ru.tsystems.tproject.services.implementation.OptionServiceImplementation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by german on 22.10.14.
 */
public class AllOptionsForNewTariffServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(AllOptionsForNewTariffServlet.class);
    @Autowired
    private OptionService optionService;
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=utf-8");
        try {
            List<Option> optionsList = optionService.getAllOptions();
            request.getSession().setAttribute("optionsList", optionsList);
            response.sendRedirect("../cp_employee/cp_employee_new_tariff.jsp");
        }
        catch (Exception ex) {
            logger.error(ex);
            request.getSession().setAttribute("exception", ex);
            response.sendRedirect("../cp_employee/exception.jsp");

        }
    }
}
