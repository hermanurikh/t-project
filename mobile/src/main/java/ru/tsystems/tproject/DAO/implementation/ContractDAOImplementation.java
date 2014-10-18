package ru.tsystems.tproject.DAO.implementation;

import ru.tsystems.tproject.DAO.API.ContractDAO;
import ru.tsystems.tproject.entities.Contract;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * Created by german on 18.10.14.
 */
public class ContractDAOImplementation implements ContractDAO {
    EntityManager entityManager;
    public ContractDAOImplementation(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    public void create(Contract contract) throws CustomDAOException {
        try {
            entityManager.persist(contract);
        } catch (PersistenceException e) {
            throw new CustomDAOException("Contract " + contract + " not created", e);
        }
    }

    @Override
    public Contract read(int id) throws CustomDAOException {
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

    public Contract getContractByNumber(long number) throws CustomDAOException {
        try {
            return (Contract) entityManager.createQuery("select c from Contract c where c.number=:number").setParameter("number", number).getSingleResult();
        } catch (PersistenceException e) {
            throw new CustomDAOException("Contract with number " + number + " not got", e);
        }
    }

    public List<Contract> getAllContracts() throws CustomDAOException {
        try {
            return entityManager.createNamedQuery("Contract.getAllContracts", Contract.class).getResultList();
        } catch (PersistenceException e) {
            throw new CustomDAOException("Couldn't get all contracts.", e);
        }
    }

}
