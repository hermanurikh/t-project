package ru.tsystems.tproject.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.exceptions.EntityNotDeletedException;

import javax.servlet.http.HttpServletRequest;

/**
 * A global exception handler to handle the exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotDeletedException.class)
    public ModelAndView handleEntityNotDeletedException(HttpServletRequest request, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex);
        modelAndView.setViewName("cp_employee/errors/exception");
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGlobalException(HttpServletRequest request, Exception ex) {
        User user = (User) request.getSession().getAttribute("currentUserU");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", ex);
        if (user.getRole().getId() == 2) {
            modelAndView.setViewName("cp_employee/errors/exception_general");
        } else {
            modelAndView.setViewName("cp_client/errors/exception");
        }
        return modelAndView;
    }

}
