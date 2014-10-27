package ru.tsystems.tproject.services.API;

import ru.tsystems.tproject.entities.Tariff;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.List;

/**
 * Basic CRUD operations along with a specific one.
 */
public interface TariffService {
    public void createTariff(Tariff tariff) throws CustomDAOException;
    public Tariff getTariffById(int id) throws CustomDAOException;
    public void updateTariff(Tariff tariff) throws CustomDAOException;
    public void deleteTariff(Tariff tariff) throws CustomDAOException;

    /**
     * get all tariffs in the database
     * @return
     * @throws CustomDAOException
     */
    public List<Tariff> getAllTariffs() throws CustomDAOException;
}
