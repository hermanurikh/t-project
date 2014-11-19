package ru.tsystems.tproject.DAO.API;

import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.List;

/**
 * Base CRUD operations along with the specific ones.
 */
public interface OptionDAO extends GenericDAO<Option, Integer> {

    /**
     * Gets all options for a tariff id.
     * @param id of the tariff
     * @return a list of possible options for the tariff
     * @throws CustomDAOException
     */
    public List<Option> getAllOptionsForTariff(int id) throws CustomDAOException;
    public List<Option> getAllOptionsForContract(int id) throws CustomDAOException;


}
