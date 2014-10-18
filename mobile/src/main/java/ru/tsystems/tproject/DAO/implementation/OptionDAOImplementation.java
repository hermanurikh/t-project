package ru.tsystems.tproject.DAO.implementation;

import ru.tsystems.tproject.DAO.API.OptionDAO;
import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Created by german on 18.10.14.
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

    @Override
    public List<Option> getAllOptionsForTariff() {
        return null;
    }
}
