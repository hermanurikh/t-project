package ru.tsystems.tproject.DAO.API;

import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.List;

/**
 * Base CRUD operations along with the specific ones.
 */
public interface OptionDAO {
    public void create(Option option) throws CustomDAOException;
    public Option read(int id) throws CustomDAOException;
    public void update(Option option) throws CustomDAOException;
    public void delete(Option option) throws CustomDAOException;

    /**
     * Gets all options from the database.
     * @return
     * @throws CustomDAOException
     */
    public List<Option> getAllOptions() throws CustomDAOException;

    /**
     * Gets all options for a tariff id.
     * @param id
     * @return
     * @throws CustomDAOException
     */
    public List<Option> getAllOptionsForTariff(int id) throws CustomDAOException;

}
