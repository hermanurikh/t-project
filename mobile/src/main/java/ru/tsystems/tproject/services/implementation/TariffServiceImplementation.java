package ru.tsystems.tproject.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.tproject.DAO.API.TariffDAO;
import ru.tsystems.tproject.entities.Tariff;
import ru.tsystems.tproject.exceptions.CustomDAOException;
import ru.tsystems.tproject.services.API.TariffService;

import java.util.List;

/**
 * An implementation of TariffService API.
 */
@Service("tariffService")
public class TariffServiceImplementation implements TariffService {
    @Autowired
    private TariffDAO tariffDAO;

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
