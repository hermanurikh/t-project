package ru.tsystems.tproject.DAO.API;

import ru.tsystems.tproject.entities.Contract;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.List;

/**
 * Base CRUD operations along with the specific ones.
 */
public interface ContractDAO {
    public void create(Contract contract) throws CustomDAOException;

    public Contract read(int id) throws CustomDAOException;

    public void update(Contract contract) throws CustomDAOException;

    public void delete(Contract contract) throws CustomDAOException;

    /**
     * Gets contract by a specified number.
     * @param number of the contract
     * @return a contract
     * @throws CustomDAOException
     */

    public Contract getContractByNumber(long number) throws CustomDAOException;

    /**
     * Gets a list of all contracts.
     * @return a list of contracts
     * @throws CustomDAOException
     */

    public List<Contract> getAllContracts() throws CustomDAOException;
}
