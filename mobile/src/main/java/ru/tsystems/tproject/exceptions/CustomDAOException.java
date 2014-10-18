package ru.tsystems.tproject.exceptions;

/**
 * Created by german on 18.10.14.
 */
public class CustomDAOException extends Exception {
    public CustomDAOException(String message)
    {

    }
    public CustomDAOException(String message, Throwable throwable)
    {
        super(message, throwable);
    }
}
