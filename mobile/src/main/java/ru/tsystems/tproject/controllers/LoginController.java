package ru.tsystems.tproject.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.services.API.UserService;
import ru.tsystems.tproject.utils.Locale.RussianLanguage;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * A controllers that validates the login data and redirects to main page.
 */
@Controller
public class LoginController {
    private final static Logger logger = Logger.getLogger(LoginController.class);
    @Autowired
    private UserService userService;

    /**
     * This method returns the login page.
     * @param locale locale;
     * @param model model;
     * @return login.jsp
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Locale locale, Model model) {
        return "login";
    }

    /**'
     * This method returns the login page when logout is required.
     * @return login.jsp
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage() {
        return "login";
    }

    /**
     * This method returns an error403 page when a restricted access page is called.
     * @return denied.jsp
     */
    @RequestMapping(value = "/denied", method = RequestMethod.GET)
    public String deniedPage() {
        return "denied";
    }

    /**
     * This method returns a login page with an error block after an unsuccessful attempt.
     * @param locale locale;
     * @param model model;
     * @return login.jsp
     */
    @RequestMapping(value = "/login-denied", method = RequestMethod.GET)
    public String loginDenied(Locale locale, Model model) {
        model.addAttribute("isInputValid", "false");
        return "login";
    }

    /**
     * This method dispatches the requests to the starting page of an employee or to the one of a user.
     * It also sets the current user entity into session.
     * @param request request;
     * @param locale locale;
     * @param model model;
     * @return cp_employee_main.jsp or cp_client_main.jsp
     */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String dispatch(HttpServletRequest request, Locale locale, Model model) {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Полученный из сессии юзер: " + user);
        logger.info("Его логин: " + user.getUsername());
        logger.info("Его права: " + user.getAuthorities());
        User currentUser = userService.getUserByLogin(user.getUsername());
        request.getSession().setAttribute("currentUserU", currentUser);
        request.getSession().setAttribute("language", RussianLanguage.getRussianLanguage());
        if (currentUser.getRole().getId() == 2) {
            return "cp_employee/cp_employee_main";
        }
        else if (currentUser.getRole().getId() == 1){
            return "cp_client/cp_client_main";
        }
        else return "login";
    }

}

