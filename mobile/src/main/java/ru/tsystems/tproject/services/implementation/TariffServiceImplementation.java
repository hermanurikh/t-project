package ru.tsystems.tproject.services.implementation;

import org.springframework.transaction.annotation.Transactional;
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
    private TariffDAO tariffDAO;
    public void setTariffDAO(TariffDAO tariffDAO) {
        this.tariffDAO = tariffDAO;
    }

    @Override
    @Transactional
    public void createTariff(Tariff tariff) throws CustomDAOException {
        this.tariffDAO.create(tariff);

    }

    @Override
    @Transactional
    public Tariff getTariffById(int id) throws CustomDAOException {
        return this.tariffDAO.read(id);
    }

    @Override
    @Transactional
    public void updateTariff(Tariff tariff) throws CustomDAOException {
        this.tariffDAO.update(tariff);

    }

    @Override
    @Transactional
    public void deleteTariff(Tariff tariff) throws CustomDAOException {
        this.tariffDAO.delete(tariff);
    }

    /**
     * A method to get all tariffs from the database.
     * @return
     * @throws CustomDAOException
     */
    @Override
    @Transactional
    public List<Tariff> getAllTariffs() throws CustomDAOException {
        return this.tariffDAO.getAllTariffs();

    }
}
