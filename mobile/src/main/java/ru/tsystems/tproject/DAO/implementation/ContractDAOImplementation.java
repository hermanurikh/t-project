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
    public void create(Contract contract) throws CustomDAOException {
        try {
            entityManager.persist(contract);
        } catch (PersistenceException e) {
            throw new CustomDAOException("Contract " + contract + " not created", e);
        }
    }

    @Override
    public Contract read(Integer id) throws CustomDAOException {
        try {
            return entityManager.find(Contract.class, id);
        } catch (PersistenceException e) {
            throw new CustomDAOException("Contract " + id + " not read", e);
        }
    }

    @Override
    public void update(Contract contract) throws CustomDAOException {
        try {
            entityManager.merge(contract);
        } catch (PersistenceException e) {
            throw new CustomDAOException("Contract " + contract + " not updated", e);
        }
    }

    @Override
    public void delete(Contract contract) throws CustomDAOException {
        try {
            entityManager.remove(contract);
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
