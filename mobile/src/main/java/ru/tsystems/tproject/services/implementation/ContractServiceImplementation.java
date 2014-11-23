package ru.tsystems.tproject.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.tproject.DAO.API.ContractDAO;
import ru.tsystems.tproject.entities.Contract;
import ru.tsystems.tproject.exceptions.ContractNotFoundException;
import ru.tsystems.tproject.exceptions.ContractsForEntityNotGotException;
import ru.tsystems.tproject.exceptions.CustomDAOException;
import ru.tsystems.tproject.services.API.ContractService;

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
    public void createEntity(Contract contract) throws CustomDAOException {
       this.contractDAO.create(contract);
    }

    @Override
    @Transactional
    public Contract getEntityById(Integer id) throws CustomDAOException {
        return this.contractDAO.read(id);
    }


    @Override
    @Transactional
    public void updateEntity(Contract contract) throws CustomDAOException {
        this.contractDAO.update(contract);
    }


    @Override
    @Transactional
    public void deleteEntity(Contract contract) throws CustomDAOException {
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
    public Contract getContractByNumber(long number) throws ContractNotFoundException {
        return this.contractDAO.getContractByNumber(number);
    }

    /**
     * A method to get all contracts.
     * @return
     * @throws CustomDAOException
     */
    @Override
    @Transactional
    public List<Contract> getAll() throws CustomDAOException {
        return this.contractDAO.getAll();
    }
    @Override
    @Transactional
    public List<Contract> getAllContractsForUser(int id) throws ContractsForEntityNotGotException {
        return this.contractDAO.getAllContractsForUser(id);
    }
}
