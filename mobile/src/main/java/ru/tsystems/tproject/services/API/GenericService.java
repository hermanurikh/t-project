package ru.tsystems.tproject.services.API;

import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.List;

/**
 * A generic service to be implemented with basic operations.
 */
public interface GenericService<E, K> {
    /**
     * A generic create service API.
     * @param entity the entity;
     * @throws CustomDAOException
     */
    public void createEntity(E entity) throws CustomDAOException;

    /**
     * A generic read service API.
     * @param id the id of the entity;
     * @return the entity;
     * @throws CustomDAOException
     */
    public E getEntityById(K id) throws CustomDAOException;

    /**
     * A generic update service API.
     * @param entity the entity;
     * @throws CustomDAOException
     */
    public void updateEntity(E entity) throws CustomDAOException;

    /**
     * A generic delete service API.
     * @param entity the entity;
     * @throws CustomDAOException
     */
    public void deleteEntity(E entity) throws CustomDAOException;

    /**
     * A generic getAll service API.
     * @return a list of entities
     * @throws CustomDAOException
     */
    public List<E> getAll() throws CustomDAOException;

}
