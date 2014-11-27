package ru.tsystems.tproject.aspect;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import ru.tsystems.tproject.entities.User;

import java.util.Arrays;

@Aspect
public class MainAspect {
    private static final Logger LOGGER = Logger.getLogger(MainAspect.class);

    @Pointcut("execution(* ru.tsystems.tproject.services.API..create*(*))")
    public void createEntityPointcut(){
        //a general pointcut of creation
    }
    @Pointcut("execution(* ru.tsystems.tproject.services.API..get*(..))")
    public void getEntityPointcut(){
        //a general pointcut of getting
    }
    @Pointcut("execution(* ru.tsystems.tproject.services.API..update*(*))")
    public void updateEntityPointcut(){
        //a general pointcut of updating
    }
    @Pointcut("execution(* ru.tsystems.tproject.services.API..delete*(*))")
    public void deleteEntityPointcut(){
        //a general pointcut of deleting
    }


    @Before("createEntityPointcut()")
    public void loggingBeforeCreateActionAdvice(JoinPoint joinPoint) {
        LOGGER.info(String.format("A method: {%s} is called, arguments passed: {%s}", joinPoint.toString(), Arrays.toString(joinPoint.getArgs())));
    }
    @After("createEntityPointcut()")
    public void loggingAfterCreateActionAdvice(JoinPoint joinPoint) {
        LOGGER.info(String.format("Entity %s is successfully created", Arrays.toString(joinPoint.getArgs())));
    }

    @Before("getEntityPointcut()")
    public void loggingBeforeGetActionAdvice(JoinPoint joinPoint) {
        LOGGER.info(String.format("A method: {%s} is called, arguments passed: {%s}", joinPoint.toString(), Arrays.toString(joinPoint.getArgs())));
    }
    @AfterReturning(pointcut = "getEntityPointcut()", returning = "user")
    public void loggingAfterGetActionAdvice(User user) {
        LOGGER.info(String.format("Entity/ies %s has been found", user));
    }

    @Before("updateEntityPointcut()")
    public void loggingBeforeUpdateActionAdvice(JoinPoint joinPoint) {
        LOGGER.info(String.format("A method: {%s} is called, arguments passed: {%s}", joinPoint.toString(), Arrays.toString(joinPoint.getArgs())));
    }
    @After("updateEntityPointcut()")
    public void loggingAfterUpdateActionAdvice(JoinPoint joinPoint) {
        LOGGER.info(String.format("Entity %s is successfully updated", Arrays.toString(joinPoint.getArgs())));
    }

    @Before("deleteEntityPointcut()")
    public void loggingBeforeDeleteActionAdvice(JoinPoint joinPoint) {
        LOGGER.info(String.format("A method: {%s} is called, arguments passed: {%s}", joinPoint.toString(), Arrays.toString(joinPoint.getArgs())));
    }
    @After("deleteEntityPointcut()")
    public void loggingAfterDeleteActionAdvice(JoinPoint joinPoint) {
        LOGGER.info(String.format("Entity %s is successfully deleted", Arrays.toString(joinPoint.getArgs())));
    }
    @AfterThrowing(value = "execution(* ru.tsystems.tproject.services..*(..))", throwing = "ex")
    public void logExceptions(JoinPoint joinPoint, Exception ex){
        LOGGER.error(String.format("Exception thrown at the method: %s, the message is {%s}", joinPoint.toString(), ex.getMessage()));
        LOGGER.error("The stack trace is below");
        for (StackTraceElement b : ex.getStackTrace()) {
            LOGGER.error("at " + b);
        }
    }




}

