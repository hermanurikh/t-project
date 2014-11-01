package ru.tsystems.tproject.filters;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.services.API.UserService;
import ru.tsystems.tproject.services.implementation.UserServiceImplementation;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This filter checks if a client tries to access employee pages. If so, he/she is redirected to the main page.
 */
public class ClientAccessFilter implements Filter {

    public void destroy() {
    }

    public void init(FilterConfig config) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        UserService userService = new UserServiceImplementation();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user;
        user = (User) request.getSession().getAttribute("currentUserU");
        if (user.getRole().getId() == 1) {
             response.sendRedirect("../cp_client/cp_client_main.jsp");
        } else filterChain.doFilter(request, response);


    }
}
