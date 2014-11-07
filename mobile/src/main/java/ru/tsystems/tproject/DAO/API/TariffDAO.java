package ru.tsystems.tproject.DAO.API;

import ru.tsystems.tproject.entities.Tariff;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.List;

/**
 * Base CRUD operations along with the specific ones.
 */
public interface TariffDAO {

    public void create(Tariff entity) throws CustomDAOException;

    public Tariff read(Integer id) throws CustomDAOException;

    public void update(Tariff entity) throws CustomDAOException;

    public void delete(Tariff entity) throws CustomDAOException;

    /**
     * Gets a list of all tariffs from the database.
     * @return a list of tariffs
     * @throws CustomDAOException
     */

    public List<Tariff> getAllTariffs() throws CustomDAOException;
}
