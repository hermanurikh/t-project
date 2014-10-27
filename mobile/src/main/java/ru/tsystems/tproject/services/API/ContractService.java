package ru.tsystems.tproject.services.API;

import ru.tsystems.tproject.entities.Contract;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.List;

/**
 * CRUD methods along with the specific ones needed for this class.
 */
public interface ContractService {
    public void createContract(Contract contract) throws CustomDAOException;
    public Contract getContractById(int id) throws CustomDAOException;
    public void updateContract(Contract contract) throws CustomDAOException;
    public void deleteContract(Contract contract) throws CustomDAOException;

    /**
     * A method to get contract by number.
     * @param number
     * @return
     * @throws CustomDAOException
     */

    public Contract getContractByNumber(long number) throws CustomDAOException;

    /**
     * A method to get all contracts in the database,
     * @return
     * @throws CustomDAOException
     */
    public List<Contract> getAllContracts() throws CustomDAOException;

}
