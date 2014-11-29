package ru.tsystems.tproject.exceptions;

/**
 * A custom exception to be thrown.
 */
public class CustomDAOException extends RuntimeException {
    public CustomDAOException(String message) {
        super(message);
    }
    public CustomDAOException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
