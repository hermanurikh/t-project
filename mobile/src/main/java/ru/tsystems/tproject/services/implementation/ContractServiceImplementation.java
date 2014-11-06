package ru.tsystems.tproject.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.tproject.DAO.API.ContractDAO;
import ru.tsystems.tproject.DAO.implementation.ContractDAOImplementation;
import ru.tsystems.tproject.entities.Contract;
import ru.tsystems.tproject.entities.Manager;
import ru.tsystems.tproject.exceptions.CustomDAOException;
import ru.tsystems.tproject.services.API.ContractService;

import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * An implementation of the API.
 */
@Service("contractService")
public class ContractServiceImplementation implements ContractService {
    @Autowired
    private ContractDAO contractDAO;
    @Override
    @Transactional
    public void createContract(Contract contract) throws CustomDAOException {
       this.contractDAO.create(contract);
    }

    @Override
    @Transactional
    public Contract getContractById(int id) throws CustomDAOException {
        return this.contractDAO.read(id);
    }


    @Override
    @Transactional
    public void updateContract(Contract contract) throws CustomDAOException {
        this.contractDAO.update(contract);
    }


    @Override
    @Transactional
    public void deleteContract(Contract contract) throws CustomDAOException {
               this.contractDAO.delete(contract);
    }

    /**
     * Gets contract by a specified number.
     * @param number
     * @return
     * @throws CustomDAOException
     */
    @Override
    @Transactional
    public Contract getContractByNumber(long number) throws CustomDAOException {
        return this.contractDAO.getContractByNumber(number);
    }

    /**
     * A method to get all contracts.
     * @return
     * @throws CustomDAOException
     */
    @Override
    @Transactional
    public List<Contract> getAllContracts() throws CustomDAOException {
        return this.contractDAO.getAllContracts();
    }
}
