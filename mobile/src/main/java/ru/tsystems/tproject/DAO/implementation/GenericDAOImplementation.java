package ru.tsystems.tproject.DAO.implementation;

import org.springframework.stereotype.Repository;
import ru.tsystems.tproject.DAO.API.GenericDAO;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * A generic DAO Implementation of main operations.
 */

public abstract class GenericDAOImplementation<E, K> implements GenericDAO<E, K> {
    protected Class<E> daoType;

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
            this.entityManager.persist(entity);
        }
        catch (PersistenceException ex)
        {
            throw new CustomDAOException("Entity not created: " + entity, ex);
        }
    }

    @Override
    public E read(K id) throws CustomDAOException {
        try {
            return (E)this.entityManager.find(daoType, id);
        }
        catch (PersistenceException ex) {
            throw new CustomDAOException("Entity with id " + id + " not found", ex);
        }
    }

    @Override
    public void update(E entity) throws CustomDAOException {
        try {
            this.entityManager.merge(entity);
        }
        catch (PersistenceException ex) {
            throw new CustomDAOException("Entity not updated: " + entity, ex);
        }

    }

    @Override
    public void delete(E entity) throws CustomDAOException {
        try {
            this.entityManager.remove(entityManager.merge(entity));
        }
        catch (PersistenceException ex) {
            throw new CustomDAOException("Entity not deleted: " + entity, ex);
        }

    }
    @Override
    public List<E> getAll() throws CustomDAOException {
        try {
            return this.entityManager.createNamedQuery(daoType.getSimpleName() + ".getAll", daoType).getResultList();
        }
        catch (PersistenceException ex) {
            throw new CustomDAOException("Unable to get all entities of class " + daoType.getSimpleName(), ex);
        }
    }


}
