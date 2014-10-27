package ru.tsystems.tproject.DAO.implementation;

import ru.tsystems.tproject.DAO.API.OptionDAO;
import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * An implementation of OptionDAO API.
 */
public class OptionDAOImplementation implements OptionDAO {
    private EntityManager entityManager;
    public OptionDAOImplementation(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    public void create(Option option) throws CustomDAOException{
        try{
            entityManager.persist(option);
        }
        catch(PersistenceException ex)
        {
            throw new CustomDAOException("Option not created: " + option, ex);
        }
    }

    @Override
    public Option read(int id) throws CustomDAOException{
        try{
            return entityManager.find(Option.class, id);
        }
        catch (PersistenceException ex)
        {
            throw new CustomDAOException("Option not found: " + id, ex);
        }
    }

    @Override
    public void update(Option option) throws CustomDAOException{
        try{
            entityManager.merge(option);
        }
        catch (PersistenceException ex)
        {
            throw new CustomDAOException("Option not updated: " + option, ex);
        }

    }

    @Override
    public void delete(Option option) throws CustomDAOException{
        try{
            entityManager.remove(option);
        }
        catch (PersistenceException ex)
        {
            throw new CustomDAOException("Option not deleted: " + option, ex);
        }

    }

    /**
     * Gets all options from the database.
     * @return
     * @throws CustomDAOException
     */
    @Override
    public List<Option> getAllOptions() throws CustomDAOException{
        try{
            return entityManager.createNamedQuery("Option.getAllOptions", Option.class).getResultList();
        }
        catch (PersistenceException ex)
        {
            throw new CustomDAOException("Options not got", ex);
        }
    }

    /**
     * Gets all available options for a specified tariff.
     * @param id
     * @return
     * @throws CustomDAOException
     */
    @Override
    public List<Option> getAllOptionsForTariff(int id) throws CustomDAOException{
        try{
            Query query = entityManager.createQuery("select t.possibleOptions from Tariff t where t.id=:id").setParameter("id", id);
            return (List<Option>)query.getResultList();
        }
        catch (PersistenceException ex)
        {
            throw new CustomDAOException("Options for tariff " + id + " not got", ex);
        }


    }

}
