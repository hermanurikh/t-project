package ru.tsystems.tproject.controllers;
import org.apache.log4j.Logger;



import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.services.API.OptionService;
import ru.tsystems.tproject.services.implementation.OptionServiceImplementation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by german on 21.10.14.
 */
public class AllOptionsServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(AllOptionsServlet.class);
    static List<Option> optionsList = new ArrayList<>();
    public void doGet(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=utf-8");
        try {
            OptionService optionService = new OptionServiceImplementation();
            List<Option> optionsList = optionService.getAllOptions();
            request.getSession().setAttribute("optionsList", optionsList);
            /*RequestDispatcher rd = request.getRequestDispatcher("../cp_employee/cp_employee_options.jsp");
            rd.forward(request, response);*/
            response.sendRedirect("../cp_employee/cp_employee_options.jsp");
        }
        catch (Exception ex) {
            response.sendRedirect("../cp_employee/exception.html");

        }
    }
}
