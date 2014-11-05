package ru.tsystems.tproject.DAO.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.tsystems.tproject.DAO.API.TariffDAO;
import ru.tsystems.tproject.entities.Tariff;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * An implementation of TariffDAO API.
 */
@Repository
public class TariffDAOImplementation implements TariffDAO {
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }

    @Override
    public void create(Tariff tariff) throws CustomDAOException {
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(tariff);
        }
        catch (PersistenceException ex)
        {
            throw new CustomDAOException("Tariff not created: " + tariff, ex);
        }
    }

    @Override
    public Tariff read(int id) throws CustomDAOException {
        try{
            Session session = this.sessionFactory.getCurrentSession();
            return (Tariff) session.load(Tariff.class, id);
        }
        catch (PersistenceException ex)
        {
            throw new CustomDAOException("Tariff with id " + id + " not found.", ex);
        }
    }

    @Override
    public void update(Tariff tariff) throws CustomDAOException {
        try{
            Session session = this.sessionFactory.getCurrentSession();
            session.update(tariff);
        }
        catch (PersistenceException ex)
        {
            throw new CustomDAOException("Tariff not updated: " + tariff, ex);
        }

    }

    @Override
    public void delete(Tariff tariff) throws CustomDAOException {
        try{
            Session session = this.sessionFactory.getCurrentSession();
            session.delete(tariff);
        }
        catch (PersistenceException ex)
        {
            throw new CustomDAOException("Tariff not deleted: " + tariff, ex);
        }

    }

    /**
     * Gets all tariffs from the database.
     * @return a list of tariffs
     * @throws CustomDAOException
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Tariff> getAllTariffs() throws CustomDAOException {
        try {
            Session session = this.sessionFactory.getCurrentSession();
            return (List<Tariff>) session.createQuery("SELECT tar FROM Tariff tar").list();
        }
        catch (PersistenceException ex)
        {
            throw new CustomDAOException("All tariffs not got", ex);
        }
    }
}
