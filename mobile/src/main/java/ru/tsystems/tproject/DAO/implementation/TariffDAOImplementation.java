package ru.tsystems.tproject.DAO.implementation;

import ru.tsystems.tproject.DAO.API.TariffDAO;
import ru.tsystems.tproject.entities.Tariff;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * An implementation of TariffDAO API.
 */
public class TariffDAOImplementation implements TariffDAO {
    private final EntityManager entityManager;
    public  TariffDAOImplementation(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    public void create(Tariff tariff) throws CustomDAOException {
        try {
            entityManager.persist(tariff);
        }
        catch (PersistenceException ex)
        {
            throw new CustomDAOException("Tariff not created: " + tariff, ex);
        }
    }

    @Override
    public Tariff read(int id) throws CustomDAOException {
        try{
            return entityManager.find(Tariff.class, id);
        }
        catch (PersistenceException ex)
        {
            throw new CustomDAOException("Tariff with id " + id + " not found.", ex);
        }
    }

    @Override
    public void update(Tariff tariff) throws CustomDAOException {
        try{
            entityManager.merge(tariff);
        }
        catch (PersistenceException ex)
        {
            throw new CustomDAOException("Tariff not updated: " + tariff, ex);
        }

    }

    @Override
    public void delete(Tariff tariff) throws CustomDAOException {
        try{
            entityManager.remove(tariff);
        }
        catch (PersistenceException ex)
        {
            throw new CustomDAOException("Tariff not deleted: " + tariff, ex);
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
