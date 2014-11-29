package ru.tsystems.tproject.DAO.API;

import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.List;

/**
 * A generic for all CRUD operations + a getAll operation. These are the operations that must exist
 * in every DAO class.
 */
public interface GenericDAO<E, K> {
    /**
     * A generic create DAO API.
     * @param entity entity to create
     * @throws CustomDAOException
     */
    public void create(E entity) throws CustomDAOException;

    /**
     * A generic read DAO API.
     * @param id the id of the entity
     * @return an entity
     * @throws CustomDAOException
     */

    public E read(K id) throws CustomDAOException;

    /**
     * A generic update DAO API.
     * @param entity the entity;
     * @throws CustomDAOException
     */
    public void update(E entity) throws CustomDAOException;

    /**
     * A generic delete DAO API.
     * @param entity the entity;
     * @throws CustomDAOException
     */
    public void delete(E entity) throws CustomDAOException;

    /**
     * A generic getAll DAO API.
     * @return a list of entities;
     * @throws CustomDAOException
     */
    public List<E> getAll() throws CustomDAOException;
}
