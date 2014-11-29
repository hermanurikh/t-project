package ru.tsystems.tproject.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.tproject.DAO.API.OptionDAO;
import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.exceptions.CustomDAOException;
import ru.tsystems.tproject.exceptions.OptionsForEntityNotGotException;
import ru.tsystems.tproject.services.API.OptionService;

import java.util.List;

/**
 * An optionService implementation.
 */
@Service("optionService")
public class OptionServiceImplementation implements OptionService {
    @Autowired
    private OptionDAO optionDAO;

    /**
     * The implementation of create entity API.
     * @param option option
     * @throws CustomDAOException
     */
    @Override
    @Transactional
    public void createEntity(Option option) throws CustomDAOException {
        this.optionDAO.create(option);
    }

    /**
     * The implementation of get entity API.
     * @param id the id of the entity;
     * @return an option
     * @throws CustomDAOException
     */
    @Override
    @Transactional
    public Option getEntityById(Integer id) throws CustomDAOException {
        return this.optionDAO.read(id);
    }

    /**
     * The implementation of update entity API
     * @param option option
     * @throws CustomDAOException
     */
    @Override
    @Transactional
    public void updateEntity(Option option) throws CustomDAOException {
        this.optionDAO.update(option);
    }

    /**
     * The implementation of delete entity API
     * @param option option
     * @throws CustomDAOException
     */
    @Override
    @Transactional
    public void deleteEntity(Option option) throws CustomDAOException {
        this.optionDAO.delete(option);
    }

    /**
     * A method to get all options from the database.
     * @return a list of options
     * @throws CustomDAOException
     */
    @Override
    @Transactional
    public List<Option> getAll() throws CustomDAOException {
        return optionDAO.getAll();
    }

    /**
     * A method to get all the options for the specified tariff ID.
     * @param id the tariff's id
     * @return a list of options
     * @throws CustomDAOException
     */
    @Override
    @Transactional
    public List<Option> getAllOptionsForTariff(int id) throws OptionsForEntityNotGotException {
        return optionDAO.getAllOptionsForTariff(id);
    }

    /**
     * A method to get all the contracts for the specified contract ID.
     * @param id contracts id;
     * @return a list of options;
     * @throws OptionsForEntityNotGotException
     */
    @Override
    @Transactional
    public List<Option> getAllOptionsForContract(int id) throws OptionsForEntityNotGotException {
        return optionDAO.getAllOptionsForContract(id);
    }

    /**
     * A method to get all the options together for the specified option ID.
     * @param id options id;
     * @return a list of options;
     * @throws OptionsForEntityNotGotException
     */
    @Override
    @Transactional
    public List<Option> getAllOptionsTogetherForOption(int id) throws OptionsForEntityNotGotException {
        return optionDAO.getAllOptionsTogetherForOption(id);
    }

    /**
     * A method to get all the options incompatible for the specified option ID.
     * @param id options id;
     * @return a list of options;
     * @throws OptionsForEntityNotGotException
     */
    @Override
    @Transactional
    public List<Option> getAllOptionsIncompatibleForOption(int id) throws OptionsForEntityNotGotException {
        return optionDAO.getAllOptionsIncompatibleForOption(id);
    }
}
