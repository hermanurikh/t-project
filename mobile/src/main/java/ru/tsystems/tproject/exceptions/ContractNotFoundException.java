package ru.tsystems.tproject.exceptions;

/**
 * An exception to be thrown when a contract is not found.
 */
public class ContractNotFoundException extends CustomDAOException {
    public ContractNotFoundException(String message) {
        super(message);
    }

    public ContractNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
