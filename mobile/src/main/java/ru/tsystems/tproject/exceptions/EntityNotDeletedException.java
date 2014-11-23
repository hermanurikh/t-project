package ru.tsystems.tproject.exceptions;

/**
 * An exception thrown when an entity which has dependencies and shouldn't be deleted, is tried to delete.
 */
public class EntityNotDeletedException extends CustomDAOException {
    public EntityNotDeletedException(String message) {
        super(message);
    }

    public EntityNotDeletedException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
