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
import javax.persistence.Query;

/**
 * An implementation of a ContractDAO API.
 */
@Repository("contractDAO")
public class ContractDAOImplementation extends GenericDAOImplementation<Contract, Integer> implements ContractDAO {
    @PersistenceContext
    private EntityManager entityManager;

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
    @SuppressWarnings("unchecked")
    @Override
    public List<Contract> getAllContractsForUser(int id) throws CustomDAOException{
        try{
            Query query = entityManager.createQuery("select u.contracts from User u where u.id=:id").setParameter("id", id);

            return (List<Contract>)query.getResultList();
        }
        catch (PersistenceException ex)
        {
            throw new CustomDAOException("Contracts for user " + id + " not got", ex);
        }
    }

}
