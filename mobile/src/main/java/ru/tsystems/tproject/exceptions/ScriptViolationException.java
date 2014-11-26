package ru.tsystems.tproject.exceptions;

/**
 * An exception to be thrown when a javascript check on page is somehow violated.
 */
public class ScriptViolationException extends CustomDAOException {


    public ScriptViolationException(String message) {
        super(message);
    }

    public ScriptViolationException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
