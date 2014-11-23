package ru.tsystems.tproject.aspect;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import ru.tsystems.tproject.entities.User;

import java.util.Arrays;

@Aspect
public class MainAspect {
    private static final Logger logger = Logger.getLogger(MainAspect.class);

    @Pointcut("execution(* ru.tsystems.tproject.services.API..create*(*))")
    public void createEntityPointcut(){}
    @Pointcut("execution(* ru.tsystems.tproject.services.API..get*(..))")
    public void getEntityPointcut(){}
    @Pointcut("execution(* ru.tsystems.tproject.services.API..update*(*))")
    public void updateEntityPointcut(){}
    @Pointcut("execution(* ru.tsystems.tproject.services.API..delete*(*))")
    public void deleteEntityPointcut(){}


    @Before("createEntityPointcut()")
    public void loggingBeforeCreateActionAdvice(JoinPoint joinPoint) {
        logger.info(String.format("A method: {%s} is called, arguments passed: {%s}", joinPoint.toString(), Arrays.toString(joinPoint.getArgs())));
    }
    @After("createEntityPointcut()")
    public void loggingAfterCreateActionAdvice(JoinPoint joinPoint) {
        logger.info(String.format("Entity %s is successfully created", Arrays.toString(joinPoint.getArgs())));
    }

    @Before("getEntityPointcut()")
    public void loggingBeforeGetActionAdvice(JoinPoint joinPoint) {
        logger.info(String.format("A method: {%s} is called, arguments passed: {%s}", joinPoint.toString(), Arrays.toString(joinPoint.getArgs())));
    }
    @AfterReturning(pointcut = "getEntityPointcut()", returning = "user")
    public void loggingAfterGetActionAdvice(User user) {
        logger.info(String.format("Entity/ies %s has been found", user));
    }

    @Before("updateEntityPointcut()")
    public void loggingBeforeUpdateActionAdvice(JoinPoint joinPoint) {
        logger.info(String.format("A method: {%s} is called, arguments passed: {%s}", joinPoint.toString(), Arrays.toString(joinPoint.getArgs())));
    }
    @After("updateEntityPointcut()")
    public void loggingAfterUpdateActionAdvice(JoinPoint joinPoint) {
        logger.info(String.format("Entity %s is successfully updated", Arrays.toString(joinPoint.getArgs())));
    }

    @Before("deleteEntityPointcut()")
    public void loggingBeforeDeleteActionAdvice(JoinPoint joinPoint) {
        logger.info(String.format("A method: {%s} is called, arguments passed: {%s}", joinPoint.toString(), Arrays.toString(joinPoint.getArgs())));
    }
    @After("deleteEntityPointcut()")
    public void loggingAfterDeleteActionAdvice(JoinPoint joinPoint) {
        logger.info(String.format("Entity %s is successfully deleted", Arrays.toString(joinPoint.getArgs())));
    }
    @AfterThrowing(value = "execution(* ru.tsystems.tproject.services..*(..))", throwing = "ex")
    public void logExceptions(JoinPoint joinPoint, Exception ex){
        logger.error(String.format("Exception thrown at the method: %s, the message is {%s}", joinPoint.toString(), ex.getMessage()));
        logger.error("The stack trace is below");
        for (StackTraceElement b : ex.getStackTrace()) {
            logger.error("at " + b);
        }
    }




}

