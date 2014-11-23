package ru.tsystems.tproject.exceptions;

/**
 * An exception to be thrown when contracts for a user had not been got.
 */
public class ContractsForEntityNotGotException extends CustomDAOException {
    public ContractsForEntityNotGotException(String message) {
        super(message);
    }

    public ContractsForEntityNotGotException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
