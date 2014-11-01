package ru.tsystems.tproject.controllers;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A class that clears all the session and cookie data.
 */
class Cookie {
    public void eraseCookie(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        javax.servlet.http.Cookie[] cookies = req.getCookies();
        if (cookies != null)
            for (javax.servlet.http.Cookie cookie : cookies) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
        req.getSession().invalidate();
        resp.sendRedirect("../login.jsp");
    }

}
