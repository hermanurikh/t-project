package ru.tsystems.tproject.controllers;

import org.apache.log4j.Logger;
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
 * Created by german on 21.10.14.
 */
public class DeleteOptionServlet extends HttpServlet {
    private static List<Option> optionsList;
    private static Logger logger = Logger.getLogger(DeleteOptionServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=utf-8");
        try {
            int optionID = Integer.parseInt(String.valueOf(request.getAttribute("optionId")));
            OptionService optionService = new OptionServiceImplementation();
            optionService.deleteOption(optionService.getOptionById(optionID));
            optionsList = optionService.getAllOptions();
            request.getSession().setAttribute("optionsList", optionsList);
            response.sendRedirect("../cp_employee/cp_employee_options.jsp");
        }
        catch (Exception ex) {
            response.sendRedirect("../cp_employee/exception.html");
        }
    }
}
