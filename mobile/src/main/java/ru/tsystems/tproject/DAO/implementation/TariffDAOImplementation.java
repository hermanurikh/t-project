package ru.tsystems.tproject.DAO.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.tsystems.tproject.DAO.API.TariffDAO;
import ru.tsystems.tproject.entities.Tariff;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * An implementation of TariffDAO API.
 */
@Repository("tariffDAO")
public class TariffDAOImplementation implements TariffDAO {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void create(Tariff entity) throws CustomDAOException {
        try {
            entityManager.persist(entity);
        }
        catch (PersistenceException ex)
        {
            throw new CustomDAOException("Entity not created: " + entity, ex);
        }
    }

    @Override
    public Tariff read(Integer id) throws CustomDAOException {
        try {
            return entityManager.find(Tariff.class, id);
        }
        catch (PersistenceException ex) {
            throw new CustomDAOException("Entity with id " + id + " not found", ex);
        }
    }

    @Override
    public void update(Tariff entity) throws CustomDAOException {
        try {
            entityManager.merge(entity);
        }
        catch (PersistenceException ex) {
            throw new CustomDAOException("Entity not updated: " + entity, ex);
        }

    }

    @Override
    public void delete(Tariff entity) throws CustomDAOException {
        try {
            entityManager.remove(entityManager.merge(entity));
        }
        catch (PersistenceException ex) {
            throw new CustomDAOException("Entity not deleted: " + entity, ex);
        }

    }

    /**
     * Gets all tariffs from the database.
     * @return a list of tariffs
     * @throws CustomDAOException
     */
    @Override
    public List<Tariff> getAllTariffs() throws CustomDAOException {
        try {
            return entityManager.createNamedQuery("Tariff.getAllTariffs", Tariff.class).getResultList();
        }
        catch (PersistenceException ex)
        {
            throw new CustomDAOException("All tariffs not got", ex);
        }
    }
}
