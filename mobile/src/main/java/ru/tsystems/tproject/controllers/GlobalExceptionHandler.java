package ru.tsystems.tproject.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.exceptions.EntityNotDeletedException;
import static ru.tsystems.tproject.utils.pages.EmployeePages.*;
import static ru.tsystems.tproject.utils.pages.ClientPages.CLIENT;

import javax.servlet.http.HttpServletRequest;

/**
 * A global exception handler to handle the exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * A handler to dispatch the request when EntityNotDeletedException occurs.
     * @param ex exception;
     * @return model and view
     */
    @ExceptionHandler(EntityNotDeletedException.class)
    public ModelAndView handleEntityNotDeletedException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(EXCEPTION, ex);
        modelAndView.setViewName(EMPLOYEE + ERRORS + EXCEPTION);
        return modelAndView;
    }

    /**
     * A handler to dispatch the request when Exception occurs.
     * @param request request;
     * @param ex exception;
     * @return model and view
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handleGlobalException(HttpServletRequest request, Exception ex) {
        User user = (User) request.getSession().getAttribute("currentUserU");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(EXCEPTION, ex);
        if (user.getRole().getId() == 2) {
            modelAndView.setViewName(EMPLOYEE + ERRORS + EXCEPTION_GENERAL);
        } else {
            modelAndView.setViewName(CLIENT + ERRORS + EXCEPTION);
        }
        return modelAndView;
    }

}
