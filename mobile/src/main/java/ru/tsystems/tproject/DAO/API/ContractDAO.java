package ru.tsystems.tproject.DAO.API;

import ru.tsystems.tproject.entities.Contract;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.List;

/**
 * Base CRUD operations along with the specific ones.
 */
public interface ContractDAO {

    public void create(Contract entity) throws CustomDAOException;

    public Contract read(Integer id) throws CustomDAOException;

    public void update(Contract entity) throws CustomDAOException;

    public void delete(Contract entity) throws CustomDAOException;
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
