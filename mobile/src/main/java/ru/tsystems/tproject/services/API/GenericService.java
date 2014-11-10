package ru.tsystems.tproject.services.API;

import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.List;

/**
 * Created by german on 10.11.14.
 */
public interface GenericService<E, K> {
    public void createEntity(E entity) throws CustomDAOException;
    public E getEntityById(K id) throws CustomDAOException;
    public void updateEntity(E entity) throws CustomDAOException;
    public void deleteEntity(E entity) throws CustomDAOException;
    public List<E> getAll() throws CustomDAOException;

}
