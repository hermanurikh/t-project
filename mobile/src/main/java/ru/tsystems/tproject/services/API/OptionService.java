package ru.tsystems.tproject.services.API;

import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.List;

/**
 * CRUD methods along with the specific ones needed for this class.
 */
public interface OptionService {
    public void createOption(Option option) throws CustomDAOException;
    public Option getOptionById(int id) throws CustomDAOException;
    public void updateOption(Option option) throws CustomDAOException;
    public void deleteOption(Option option) throws CustomDAOException;

    /**
     * get all options from the database
     * @return
     * @throws CustomDAOException
     */
    public List<Option> getAllOptions() throws CustomDAOException;

    /**
     * get all options for a specific tariff
     * @param id
     * @return
     * @throws CustomDAOException
     */
    public List<Option> getAllOptionsForTariff(int id) throws CustomDAOException;
}
