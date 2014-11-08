package ru.tsystems.tproject.DAO.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.tsystems.tproject.DAO.API.ContractDAO;
import ru.tsystems.tproject.entities.Contract;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * An implementation of a ContractDAO API.
 */
@Repository("contractDAO")
public class ContractDAOImplementation implements ContractDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Contract entity) throws CustomDAOException {
        try {
            entityManager.persist(entity);
        }
        catch (PersistenceException ex)
        {
            throw new CustomDAOException("Entity not created: " + entity, ex);
        }
    }

    @Override
    public Contract read(Integer id) throws CustomDAOException {
        try {
            return entityManager.find(Contract.class, id);
        }
        catch (PersistenceException ex) {
            throw new CustomDAOException("Entity with id " + id + " not found", ex);
        }
    }

    @Override
    public void update(Contract entity) throws CustomDAOException {
        try {
            entityManager.merge(entity);
        }
        catch (PersistenceException ex) {
            throw new CustomDAOException("Entity not updated: " + entity, ex);
        }

    }

    @Override
    public void delete(Contract entity) throws CustomDAOException {
        try {
            entityManager.remove(entityManager.merge(entity));
        }
        catch (PersistenceException ex) {
            throw new CustomDAOException("Entity not deleted: " + entity, ex);
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
            return (Contract) entityManager.createQuery("select c from Contract c where c.number=:number").setParameter("number", number).getSingleResult();
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
            return entityManager.createNamedQuery("Contract.getAllContracts", Contract.class).getResultList();
        } catch (PersistenceException e) {
            throw new CustomDAOException("Couldn't get all contracts.", e);
        }
    }

}
