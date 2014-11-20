package ru.tsystems.tproject.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final static Logger logger = Logger.getLogger(LoginController.class);
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
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String dispatch(HttpServletRequest request, Locale locale, Model model) {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.error("Полученный из сессии юзер: " + user);
        logger.error("Его логин: " + user.getUsername());
        logger.error("Его права: " + user.getAuthorities());
        User currentUser = userService.getUserByLogin(user.getUsername());
        request.getSession().setAttribute("currentUserU", currentUser);
        if (currentUser.getRole().getId() == 2) {
            return "cp_employee/cp_employee_main";
        }
        else if (currentUser.getRole().getId() == 1){
            return "cp_client/cp_client_main";
        }
        else return "login";
    }

}

