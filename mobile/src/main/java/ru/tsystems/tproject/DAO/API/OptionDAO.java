package ru.tsystems.tproject.DAO.API;

import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.exceptions.CustomDAOException;
import ru.tsystems.tproject.exceptions.OptionsForEntityNotGotException;

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
    public List<Option> getAllOptionsForTariff(int id) throws OptionsForEntityNotGotException;

    /**
     * Gets all options for a contract id
     * @param id the id of the contract;
     * @return a list of options
     * @throws OptionsForEntityNotGotException
     */
    public List<Option> getAllOptionsForContract(int id) throws OptionsForEntityNotGotException;

    /**
     * Gets all options that should go together with a specified option
     * @param id the id of the option;
     * @return a list of options;
     * @throws OptionsForEntityNotGotException
     */
    public List<Option> getAllOptionsTogetherForOption(int id) throws OptionsForEntityNotGotException;

    /**
     * Gets all options that should not go together with a specified option.
     * @param id the id of the option;
     * @return a list of options;
     * @throws OptionsForEntityNotGotException
     */
    public List<Option> getAllOptionsIncompatibleForOption(int id) throws OptionsForEntityNotGotException;




}
