package ru.tsystems.tproject.DAO.implementation;

import ru.tsystems.tproject.DAO.API.GenericDAO;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.lang.reflect.ParameterizedType;

/**
 * Created by german on 07.11.14.
 */
public class GenericDAOImplementation<E, K> implements GenericDAO <E, K>{
    protected Class<? extends E> daoType;

    @SuppressWarnings("unchecked")
    public GenericDAOImplementation() {
        daoType = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(E entity) throws CustomDAOException {
        try {
            entityManager.persist(entity);
        }
        catch (PersistenceException ex)
        {
            throw new CustomDAOException("Entity not created: " + entity, ex);
        }
    }

    @Override
    public E read(K id) throws CustomDAOException {
        try {
            return entityManager.find(daoType, id);
        }
        catch (PersistenceException ex) {
            throw new CustomDAOException("Entity with id " + id + " not found", ex);
        }
    }

    @Override
    public void update(E entity) throws CustomDAOException {
        try {
            entityManager.merge(entity);
        }
        catch (PersistenceException ex) {
            throw new CustomDAOException("Entity not updated: " + entity, ex);
        }

    }

    @Override
    public void delete(E entity) throws CustomDAOException {
        try {
            entityManager.remove(entityManager.merge(entity));
        }
        catch (PersistenceException ex) {
            throw new CustomDAOException("Entity not deleted: " + entity, ex);
        }

    }


}
