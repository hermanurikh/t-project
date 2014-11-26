package ru.tsystems.tproject.services.API;

import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.List;

/**
 * A generic service to be implemented with basic operations.
 */
public interface GenericService<E, K> {
    public void createEntity(E entity) throws CustomDAOException;
    public E getEntityById(K id) throws CustomDAOException;
    public void updateEntity(E entity) throws CustomDAOException;
    public void deleteEntity(E entity) throws CustomDAOException;
    public List<E> getAll() throws CustomDAOException;

}
