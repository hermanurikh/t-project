package ru.tsystems.tproject.DAO.API;

import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.List;

/**
 * A generic for all CRUD operations.
 */
public interface GenericDAO<E, K> {

    public void create(E entity) throws CustomDAOException;

    public E read(K id) throws CustomDAOException;

    public void update(E entity) throws CustomDAOException;

    public void delete(E entity) throws CustomDAOException;
}
