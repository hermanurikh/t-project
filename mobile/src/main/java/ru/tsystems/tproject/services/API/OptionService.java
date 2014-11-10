package ru.tsystems.tproject.services.API;

import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.List;

/**
 * CRUD methods along with the specific ones needed for this class.
 */
public interface OptionService extends GenericService<Option, Integer> {

    /**
     * get all options for a specific tariff
     * @param id
     * @return
     * @throws CustomDAOException
     */
    public List<Option> getAllOptionsForTariff(int id) throws CustomDAOException;
}
