package ru.tsystems.tproject.DAO.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.tsystems.tproject.DAO.API.OptionDAO;
import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.exceptions.CustomDAOException;
import ru.tsystems.tproject.exceptions.OptionsForEntityNotGotException;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * An implementation of OptionDAO API.
 */
@Repository("optionDAO")
public class OptionDAOImplementation extends GenericDAOImplementation<Option, Integer> implements OptionDAO {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Gets all available options for a specified tariff.
     * @param id - id of the tariff
     * @return a list of options for the tariff
     * @throws CustomDAOException
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Option> getAllOptionsForTariff(int id) throws OptionsForEntityNotGotException {
        try{
            Query query = entityManager.createQuery("select t.possibleOptions from Tariff t where t.id=:id").setParameter("id", id);

            return (List<Option>)query.getResultList();
        }
        catch (PersistenceException ex)
        {
            throw new OptionsForEntityNotGotException("Options for tariff " + id + " not got", ex);
        }
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Option> getAllOptionsForContract(int id) throws OptionsForEntityNotGotException{
        try{
            Query query = entityManager.createQuery("select c.options from Contract c where c.id=:id").setParameter("id", id);

            return (List<Option>)query.getResultList();
        }
        catch (PersistenceException ex)
        {
            throw new OptionsForEntityNotGotException("Options for contract " + id + " not got", ex);
        }
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Option> getAllOptionsTogetherForOption(int id) throws OptionsForEntityNotGotException{
        try{
            Query query = entityManager.createQuery("select opt.optionsTogether from Option opt where opt.id=:id").setParameter("id", id);

            return (List<Option>)query.getResultList();
        }
        catch (PersistenceException ex)
        {
            throw new OptionsForEntityNotGotException("Options for tariff " + id + " not got", ex);
        }
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Option> getAllOptionsIncompatibleForOption(int id) throws OptionsForEntityNotGotException{
        try{
            Query query = entityManager.createQuery("select opt.optionsIncompatible from Option opt where opt.id=:id").setParameter("id", id);

            return (List<Option>)query.getResultList();
        }
        catch (PersistenceException ex)
        {
            throw new OptionsForEntityNotGotException("Options for tariff " + id + " not got", ex);
        }
    }

}
