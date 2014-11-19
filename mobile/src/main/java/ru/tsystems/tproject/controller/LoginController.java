package ru.tsystems.tproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.services.API.UserService;
import ru.tsystems.tproject.utils.Converter;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * A controller that validates the login data and redirects to main page.
 */
@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Locale locale, Model model) {
        return "login";
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage() {
        return "login";
    }
    @RequestMapping(value = "/denied", method = RequestMethod.GET)
    public String deniedPage() {
        return "denied";
    }
    @RequestMapping(value = "/login-denied", method = RequestMethod.GET)
    public String loginDenied(Locale locale, Model model) {
        model.addAttribute("isInputValid", "false");
        return "login";
    }
    /*@RequestMapping(value = "/main", method = RequestMethod.GET)
    public String dispatch(HttpServletRequest request, Locale locale, Model model) {
        pageContext.request.userPrincipal.username
    }*/
}

