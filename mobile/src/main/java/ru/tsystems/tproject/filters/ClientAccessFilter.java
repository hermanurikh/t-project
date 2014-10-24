package ru.tsystems.tproject.filters;

import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.services.API.UserService;
import ru.tsystems.tproject.services.implementation.UserServiceImplementation;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by german on 24.10.14.
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
        try {
            user = (User) request.getSession().getAttribute("currentUserObject");
            if (user.getRole().getId() == 1) {
                response.sendRedirect("../cp_client/cp_client_main.html");
            } else filterChain.doFilter(request, response);
        } catch (Exception ex) {
            response.sendRedirect("../login.jsp");
        }
    }
}
