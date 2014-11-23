package ru.tsystems.tproject.exceptions;

/**
 * An exception to be thrown when a user is not found.
 */
public class UserNotFoundException extends CustomDAOException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
