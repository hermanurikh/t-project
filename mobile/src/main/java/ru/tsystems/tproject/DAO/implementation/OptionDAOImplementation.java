package ru.tsystems.tproject.DAO.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
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
@Repository
public class OptionDAOImplementation implements OptionDAO {
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }

    @Override
    public void create(Option option) throws CustomDAOException{
        try{
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(option);
        }
        catch(PersistenceException ex)
        {
            throw new CustomDAOException("Option not created: " + option, ex);
        }
    }

    @Override
    public Option read(int id) throws CustomDAOException{
        try{
            Session session = this.sessionFactory.getCurrentSession();
            return (Option) session.load(Option.class, id);

        }
        catch (PersistenceException ex)
        {
            throw new CustomDAOException("Option not found: " + id, ex);
        }
    }

    @Override
    public void update(Option option) throws CustomDAOException{
        try{
            Session session = this.sessionFactory.getCurrentSession();
            session.update(option);
        }
        catch (PersistenceException ex)
        {
            throw new CustomDAOException("Option not updated: " + option, ex);
        }

    }

    @Override
    public void delete(Option option) throws CustomDAOException{
        try{
            Session session = this.sessionFactory.getCurrentSession();
            session.delete(option);
        }
        catch (PersistenceException ex)
        {
            throw new CustomDAOException("Option not deleted: " + option, ex);
        }

    }

    /**
     * Gets all options from the database.
     * @return a list of options
     * @throws CustomDAOException
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Option> getAllOptions() throws CustomDAOException{
        try{
            Session session = this.sessionFactory.getCurrentSession();
            return (List<Option>) session.createQuery("SELECT opt FROM Option opt").list();
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
            Session session = this.sessionFactory.getCurrentSession();

            return (List<Option>) session.createQuery("select t.possibleOptions from Tariff t where t.id=:id").setParameter("id", id).list();

        }
        catch (PersistenceException ex)
        {
            throw new CustomDAOException("Options for tariff " + id + " not got", ex);
        }


    }

}
