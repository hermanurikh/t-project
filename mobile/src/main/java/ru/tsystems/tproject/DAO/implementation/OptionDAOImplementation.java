package ru.tsystems.tproject.DAO.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.tsystems.tproject.DAO.API.OptionDAO;
import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.exceptions.CustomDAOException;

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
     * Gets all options from the database.
     * @return a list of options
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
     * @param id - id of the tariff
     * @return a list of options for the tariff
     * @throws CustomDAOException
     */
    @SuppressWarnings("unchecked")
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
