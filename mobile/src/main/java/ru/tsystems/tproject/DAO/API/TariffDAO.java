package ru.tsystems.tproject.DAO.API;

import ru.tsystems.tproject.entities.Tariff;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.List;

/**
 * Base CRUD operations along with the specific ones.
 */
public interface TariffDAO {

    public void create(Tariff tariff) throws CustomDAOException;

    public Tariff read(int id) throws CustomDAOException;

    public void update(Tariff tariff) throws CustomDAOException;

    public void delete(Tariff tariff) throws CustomDAOException;

    /**
     * Gets a list of all tariffs from the database.
     * @return a list of tariffs
     * @throws CustomDAOException
     */

    public List<Tariff> getAllTariffs() throws CustomDAOException;
}
