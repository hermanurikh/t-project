package ru.tsystems.tproject.aspect;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

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

    /**
     * Logging before creation
     * @param joinPoint a joinPoint;
     */
    @Before("createEntityPointcut()")
    public void loggingBeforeCreateActionAdvice(JoinPoint joinPoint) {
        LOGGER.info(String.format("A method: {%s} is called, arguments passed: {%s}", joinPoint.toString(), Arrays.toString(joinPoint.getArgs())));
    }

    /**
     * Logging after creation
     * @param joinPoint a joinPoint;
     */
    @After("createEntityPointcut()")
    public void loggingAfterCreateActionAdvice(JoinPoint joinPoint) {
        LOGGER.info(String.format("Entity %s is successfully created", Arrays.toString(joinPoint.getArgs())));
    }

    /**
     * Logging before getting
     * @param joinPoint a joinPoint;
     */
    @Before("getEntityPointcut()")
    public void loggingBeforeGetActionAdvice(JoinPoint joinPoint) {
        LOGGER.info(String.format("A method: {%s} is called, arguments passed: {%s}", joinPoint.toString(), Arrays.toString(joinPoint.getArgs())));
    }

    /**
     * Logging after getting
     * @param object the entity found
     */
    @AfterReturning(pointcut = "getEntityPointcut()", returning = "object")
    public void loggingAfterGetActionAdvice(Object object) {
        LOGGER.info(String.format("Entity/ies %s has been found", object));
    }

    /**
     * Logging before updating
     * @param joinPoint a joinPoint;
     */
    @Before("updateEntityPointcut()")
    public void loggingBeforeUpdateActionAdvice(JoinPoint joinPoint) {
        LOGGER.info(String.format("A method: {%s} is called, arguments passed: {%s}", joinPoint.toString(), Arrays.toString(joinPoint.getArgs())));
    }

    /**
     * Logging after updating
     * @param joinPoint a joinPoint;
     */
    @After("updateEntityPointcut()")
    public void loggingAfterUpdateActionAdvice(JoinPoint joinPoint) {
        LOGGER.info(String.format("Entity %s is successfully updated", Arrays.toString(joinPoint.getArgs())));
    }

    /**
     * Logging before deleting
     * @param joinPoint a joinPoint;
     */
    @Before("deleteEntityPointcut()")
    public void loggingBeforeDeleteActionAdvice(JoinPoint joinPoint) {
        LOGGER.info(String.format("A method: {%s} is called, arguments passed: {%s}", joinPoint.toString(), Arrays.toString(joinPoint.getArgs())));
    }

    /**
     * Logging after deleting
     * @param joinPoint a joinPoint;
     */
    @After("deleteEntityPointcut()")
    public void loggingAfterDeleteActionAdvice(JoinPoint joinPoint) {
        LOGGER.info(String.format("Entity %s is successfully deleted", Arrays.toString(joinPoint.getArgs())));
    }

    /**
     * Logging possible exception
     * @param joinPoint a joinPoint;
     * @param ex exception
     */
    @AfterThrowing(value = "execution(* ru.tsystems.tproject.services..*(..))", throwing = "ex")
    public void logExceptions(JoinPoint joinPoint, Exception ex){
        LOGGER.error(String.format("Exception thrown at the method: %s, the message is {%s}", joinPoint.toString(), ex.getMessage()));
        LOGGER.error("The stack trace is below");
        for (StackTraceElement b : ex.getStackTrace()) {
            LOGGER.error("at " + b);
        }
    }




}

