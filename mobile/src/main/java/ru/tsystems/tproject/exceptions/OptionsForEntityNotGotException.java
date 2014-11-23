package ru.tsystems.tproject.exceptions;

/**
 * An exception thrown when a method tries to get a list of options for another option, or tariff,
 * or contract, and fails.
 */
public class OptionsForEntityNotGotException extends CustomDAOException{
    public OptionsForEntityNotGotException(String message) {
        super(message);
    }

    public OptionsForEntityNotGotException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
