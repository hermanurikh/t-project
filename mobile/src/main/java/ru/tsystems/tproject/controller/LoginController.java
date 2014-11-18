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
    @RequestMapping(value = "/login-check-data", method = RequestMethod.POST)
    public String validateData(@RequestParam(value = "username") String username,
                               @RequestParam(value = "password") String password,
                               HttpServletRequest request, Locale locale, Model model) {
        try {
            User user = userService.getUserByLogin(username);
            if (user == null) throw new Exception("There is no user with the login " + username);
            else {
                if (user.getPassword().equals(Converter.getMD5(password))) {
                    request.getSession().setAttribute("currentUserU", user);
                    if (user.getRole().getId() == 1) {
                        return "cp_client/cp_client_main";
                    } else if (user.getRole().getId() == 2) {
                        return "cp_employee/cp_employee_main";
                    } else {
                        throw new Exception("The role of user is undefined");
                    }
                } else throw new Exception("The users passwords do not match");
            }
        }
        catch (Exception exception) {
            request.getSession().invalidate();
            model.addAttribute("isInputValid", "false");
            return "login";
        }

    }
}

