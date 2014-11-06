package ru.tsystems.tproject.DAO.API;

import ru.tsystems.tproject.entities.Tariff;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.List;

/**
 * Base CRUD operations along with the specific ones.
 */
public interface TariffDAO extends GenericDAO<Tariff, Integer> {

    /**
     * Gets a list of all tariffs from the database.
     * @return a list of tariffs
     * @throws CustomDAOException
     */

    public List<Tariff> getAllTariffs() throws CustomDAOException;
}
