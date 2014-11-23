package ru.tsystems.tproject.services.API;

import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.exceptions.CustomDAOException;
import ru.tsystems.tproject.exceptions.OptionsForEntityNotGotException;

import java.util.List;

/**
 * CRUD methods along with the specific ones needed for this class.
 */
public interface OptionService extends GenericService<Option, Integer> {

    /**
     * get all options for a specific tariff
     * @param id tariffs id
     * @return a list of options
     * @throws CustomDAOException
     */
    public List<Option> getAllOptionsForTariff(int id) throws OptionsForEntityNotGotException;

    /**
     * Get all options for a specified contract.
     * @param id contracts id
     * @return a list of options
     * @throws CustomDAOException
     */
    public List<Option> getAllOptionsForContract(int id) throws OptionsForEntityNotGotException;
    public List<Option> getAllOptionsTogetherForOption(int id) throws OptionsForEntityNotGotException;
    public List<Option> getAllOptionsIncompatibleForOption(int id) throws OptionsForEntityNotGotException;
}
