package ru.tsystems.tproject.DAO.implementation;

import ru.tsystems.tproject.DAO.API.GenericDAO;
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

    /**
     * A generic create operation.
     * @param entity the entity
     * @throws CustomDAOException
     */
    @Override
    public void create(E entity) throws CustomDAOException {
        try {
            this.entityManager.persist(entity);
        } catch (PersistenceException ex) {
            throw new CustomDAOException("Entity not created: " + entity, ex);
        }
    }

    /**
     * A generic read operation.
     * @param id the id of the entity;
     * @return the entity
     * @throws CustomDAOException
     */
    @Override
    public E read(K id) throws CustomDAOException {
        try {
            return (E)this.entityManager.find(daoType, id);
        } catch (PersistenceException ex) {
            throw new CustomDAOException("Entity with id " + id + " not found", ex);
        }
    }

    /**
     * A generic update operation.
     * @param entity the entity;
     * @throws CustomDAOException
     */
    @Override
    public void update(E entity) throws CustomDAOException {
        try {
            this.entityManager.merge(entity);
        } catch (PersistenceException ex) {
            throw new CustomDAOException("Entity not updated: " + entity, ex);
        }

    }

    /**
     * A generic delete operation.
     * @param entity the entity;
     * @throws CustomDAOException
     */
    @Override
    public void delete(E entity) throws CustomDAOException {
        try {
            this.entityManager.remove(entityManager.merge(entity));
        } catch (PersistenceException ex) {
            throw new CustomDAOException("Entity not deleted: " + entity, ex);
        }

    }

    /**
     * A generic getAll operation
     * @return a list of entities.
     * @throws CustomDAOException
     */
    @Override
    public List<E> getAll() throws CustomDAOException {
        try {
            return this.entityManager.createNamedQuery(daoType.getSimpleName() + ".getAll", daoType).getResultList();
        } catch (PersistenceException ex) {
            throw new CustomDAOException("Unable to get all entities of class " + daoType.getSimpleName(), ex);
        }
    }


}
