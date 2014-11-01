package ru.tsystems.tproject.services.implementation;

import ru.tsystems.tproject.DAO.API.TariffDAO;
import ru.tsystems.tproject.DAO.implementation.TariffDAOImplementation;
import ru.tsystems.tproject.entities.Manager;
import ru.tsystems.tproject.entities.Tariff;
import ru.tsystems.tproject.exceptions.CustomDAOException;
import ru.tsystems.tproject.services.API.TariffService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * An implementation of TariffService API.
 */
public class TariffServiceImplementation implements TariffService {
    private final EntityManager entityManager = Manager.getEntityManager();
    private final TariffDAO tariffDAO = new TariffDAOImplementation(entityManager);

    public void createTariff(Tariff tariff) throws CustomDAOException {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            tariffDAO.create(tariff);
            entityTransaction.commit();
        }
        catch (RuntimeException ex)
        {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new CustomDAOException("Unable to create tariff: " + tariff, ex);
        }

    }

    @Override
    public Tariff getTariffById(int id) throws CustomDAOException {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Tariff tariff = tariffDAO.read(id);
            entityTransaction.commit();
            if (tariff == null) throw new CustomDAOException("Tariff with id " + id + " not found");
            else return  tariff;
        }
        catch (RuntimeException ex)
        {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new CustomDAOException("Unable to read tariff with id: " + id, ex);
        }
    }

    @Override
    public void updateTariff(Tariff tariff) throws CustomDAOException {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            tariffDAO.update(tariff);
            entityTransaction.commit();
        }
        catch (RuntimeException ex)
        {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new CustomDAOException("Unable to update tariff: " + tariff, ex);
        }

    }

    @Override
    public void deleteTariff(Tariff tariff) throws CustomDAOException {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            tariffDAO.delete(tariff);
            entityTransaction.commit();
        }
        catch (RuntimeException ex)
        {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new CustomDAOException("Unable to delete tariff: " + tariff, ex);
        }
    }

    /**
     * A method to get all tariffs from the database.
     * @return
     * @throws CustomDAOException
     */
    @Override
    public List<Tariff> getAllTariffs() throws CustomDAOException {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            List<Tariff> list = tariffDAO.getAllTariffs();
            entityTransaction.commit();
            return list;
        }
        catch (RuntimeException ex)
        {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new CustomDAOException("Unable to get all tariffs", ex);
        }

    }
}
