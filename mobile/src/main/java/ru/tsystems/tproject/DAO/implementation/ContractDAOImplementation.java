package ru.tsystems.tproject.DAO.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.tsystems.tproject.DAO.API.ContractDAO;
import ru.tsystems.tproject.entities.Contract;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * An implementation of a ContractDAO API.
 */
@Repository
public class ContractDAOImplementation implements ContractDAO {
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }


    @Override
    public void create(Contract contract) throws CustomDAOException {
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(contract);
        } catch (PersistenceException e) {
            throw new CustomDAOException("Contract " + contract + " not created", e);
        }
    }

    @Override
    public Contract read(int id) throws CustomDAOException {
        try {
            Session session = this.sessionFactory.getCurrentSession();
            return (Contract) session.load(Contract.class, id);
        } catch (PersistenceException e) {
            throw new CustomDAOException("Contract " + id + " not read", e);
        }
    }

    @Override
    public void update(Contract contract) throws CustomDAOException {
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.update(contract);
        } catch (PersistenceException e) {
            throw new CustomDAOException("Contract " + contract + " not updated", e);
        }
    }

    @Override
    public void delete(Contract contract) throws CustomDAOException {
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.delete(contract);
        } catch (PersistenceException e) {
            throw new CustomDAOException("Contract " + contract + " not deleted", e);
        }
    }

    /**
     * Gets contract by a specified number.
     * @param number - a number of the contract
     * @return contract
     * @throws CustomDAOException
     */
    @Override
    public Contract getContractByNumber(long number) throws CustomDAOException {
        try {
            Session session = this.sessionFactory.getCurrentSession();
            return (Contract) session.createQuery("select c from Contract c where c.number=:number").setParameter("number", number).uniqueResult();
        } catch (PersistenceException e) {
            throw new CustomDAOException("Contract with number " + number + " not got", e);
        }
    }

    /**
     * Gets all contracts from the database.
     * @return a list of contracts
     * @throws CustomDAOException
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Contract> getAllContracts() throws CustomDAOException {
        try {
            Session session = this.sessionFactory.getCurrentSession();
            return session.createQuery("select c from Contract c").list();
        } catch (PersistenceException e) {
            throw new CustomDAOException("Couldn't get all contracts.", e);
        }
    }

}
