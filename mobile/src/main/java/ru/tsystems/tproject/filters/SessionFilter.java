package ru.tsystems.tproject.filters;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.exceptions.CustomDAOException;
import ru.tsystems.tproject.services.API.UserService;
import ru.tsystems.tproject.services.implementation.UserServiceImplementation;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This filter checks if the session is valid by checking the user's id. If there is none, it redirects to the login page.
 */
public class SessionFilter implements Filter {
    public void destroy(){}
    public void init(FilterConfig config){}
    private static Logger logger = Logger.getLogger(SessionFilter.class);
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        UserService userService = new UserServiceImplementation();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user = null;
        try {
            user = (User) request.getSession().getAttribute("currentUserU");
            if (user == null ) {
                throw new CustomDAOException("the ID is null!");
            }
            else {
                filterChain.doFilter(request, response);
            }
        }
        catch (CustomDAOException ex) {
            logger.error(ex);
            response.sendRedirect("../login.jsp");
        }
    }
}
