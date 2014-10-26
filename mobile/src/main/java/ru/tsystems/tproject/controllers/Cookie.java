package ru.tsystems.tproject.controllers;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.services.API.UserService;
import ru.tsystems.tproject.services.implementation.UserServiceImplementation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * A class that clears all the session and cookie data.
 */
public class Cookie {
    private static UserService userService = new UserServiceImplementation();
    private static Logger logger = Logger.getLogger(Cookie.class);
    public void eraseCookie(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        javax.servlet.http.Cookie[] cookies = req.getCookies();
        if (cookies != null)
            for (int i = 0; i < cookies.length; i++) {
                cookies[i].setValue("");
                cookies[i].setPath("/");
                cookies[i].setMaxAge(0);
                resp.addCookie(cookies[i]);
            }
        req.getSession().invalidate();
        resp.sendRedirect("../login.jsp");
    }
    public User validateSession(HttpSession session) throws Exception{
        User user = null;
        try {
            user = userService.getUserById(Integer.parseInt(String.valueOf(session.getAttribute("currentUserUID"))));
        }
        catch (Exception ex) {
            logger.error("invalid session!", ex);
            throw ex;
        }

        return user;
    }
}
