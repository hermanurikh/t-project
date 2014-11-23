package ru.tsystems.tproject.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ru.tsystems.tproject.exceptions.EntityNotDeletedException;

import javax.servlet.http.HttpServletRequest;

/**
 * A global exception handler to handle the exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {


}
