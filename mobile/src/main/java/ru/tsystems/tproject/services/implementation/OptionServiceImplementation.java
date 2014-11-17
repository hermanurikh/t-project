package ru.tsystems.tproject.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.tproject.DAO.API.OptionDAO;
import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.exceptions.CustomDAOException;
import ru.tsystems.tproject.services.API.OptionService;

import java.util.List;

/**
 * Created by german on 19.10.14.
 */
@Service("optionService")
public class OptionServiceImplementation implements OptionService {
    @Autowired
    private OptionDAO optionDAO;

    @Override
    @Transactional
    public void createEntity(Option option) throws CustomDAOException {
        this.optionDAO.create(option);
    }

    @Override
    @Transactional
    public Option getEntityById(Integer id) throws CustomDAOException {
        return this.optionDAO.read(id);
    }

    @Override
    @Transactional
    public void updateEntity(Option option) throws CustomDAOException {
        this.optionDAO.update(option);
    }

    @Override
    @Transactional
    public void deleteEntity(Option option) throws CustomDAOException {
        this.optionDAO.delete(option);
    }

    /**
     * A method to get all options from the database.
     * @return
     * @throws CustomDAOException
     */
    @Override
    @Transactional
    public List<Option> getAll() throws CustomDAOException {
        return optionDAO.getAll();
    }

    /**
     * A method to get all the options for the specified tariff ID.
     * @param id
     * @return
     * @throws CustomDAOException
     */
    @Override
    @Transactional
    public List<Option> getAllOptionsForTariff(int id) throws CustomDAOException {
        return optionDAO.getAllOptionsForTariff(id);
    }
}
