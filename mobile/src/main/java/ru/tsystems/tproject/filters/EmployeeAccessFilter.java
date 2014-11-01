package ru.tsystems.tproject.filters;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This filter checks if an employee tries to access client pages. If so, he/she is redirected to the main page.
 */
public class EmployeeAccessFilter implements Filter {

    public void destroy(){}
    public void init(FilterConfig config){}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user;

            user = (User) request.getSession().getAttribute("currentUserU");
            if (user.getRole().getId() == 2) {
            response.sendRedirect("../cp_employee/cp_employee_main.jsp");
            }
            else filterChain.doFilter(request, response);

    }
}