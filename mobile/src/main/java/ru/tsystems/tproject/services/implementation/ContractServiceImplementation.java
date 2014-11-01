package ru.tsystems.tproject.services.implementation;

import ru.tsystems.tproject.DAO.API.ContractDAO;
import ru.tsystems.tproject.DAO.implementation.ContractDAOImplementation;
import ru.tsystems.tproject.entities.Contract;
import ru.tsystems.tproject.entities.Manager;
import ru.tsystems.tproject.exceptions.CustomDAOException;
import ru.tsystems.tproject.services.API.ContractService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * An implementation of the API.
 */
public class ContractServiceImplementation implements ContractService {
    private final EntityManager entityManager = Manager.getEntityManager();
    private final ContractDAO contractDAO = new ContractDAOImplementation(entityManager);

    @Override
    public void createContract(Contract contract) throws CustomDAOException {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            contractDAO.create(contract);
            entityTransaction.commit();
        }
        catch (RuntimeException ex) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new CustomDAOException("Unable to create a contract: " + contract, ex);
        }
    }

    @Override
    public Contract getContractById(int id) throws CustomDAOException {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Contract contract = contractDAO.read(id);
            entityTransaction.commit();
            if (contract == null) {
                throw new CustomDAOException("Contract with id " + id + " not found");
            }
            else return contract;
        }
        catch (RuntimeException ex) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new CustomDAOException("Unable to get a contract with id: " + id, ex);
        }
    }

    @Override
    public void updateContract(Contract contract) throws CustomDAOException {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            contractDAO.update(contract);
            entityTransaction.commit();
        }
        catch (RuntimeException ex) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new CustomDAOException("Unable to update a contract: " + contract, ex);
        }
    }

    @Override
    public void deleteContract(Contract contract) throws CustomDAOException {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            contractDAO.delete(contract);
            entityTransaction.commit();
        }
        catch (RuntimeException ex) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new CustomDAOException("Unable to delete a contract: " + contract, ex);
        }
    }

    /**
     * Gets contract by a specified number.
     * @param number
     * @return
     * @throws CustomDAOException
     */
    @Override
    public Contract getContractByNumber(long number) throws CustomDAOException {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Contract contract = contractDAO.getContractByNumber(number);
            entityTransaction.commit();
            if (contract == null) {
                throw new CustomDAOException("Contract with number " + number + " not found");
            }
            else return contract;
        }
        catch (RuntimeException ex) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new CustomDAOException("Unable to get a contract with number: " + number, ex);
        }
    }

    /**
     * A method to get all contracts.
     * @return
     * @throws CustomDAOException
     */
    @Override
    public List<Contract> getAllContracts() throws CustomDAOException {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            List<Contract> list = contractDAO.getAllContracts();
            entityTransaction.commit();
            return list;
        }
        catch (RuntimeException ex) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new CustomDAOException("Unable to get all contracts", ex);
        }
    }
}
