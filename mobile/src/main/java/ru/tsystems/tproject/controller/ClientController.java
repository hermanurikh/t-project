package ru.tsystems.tproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.tsystems.tproject.services.API.UserService;

/**
 * Created by german on 11/11/14.
 */
@Controller
@RequestMapping(value = "main")
public class ClientController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }
}
