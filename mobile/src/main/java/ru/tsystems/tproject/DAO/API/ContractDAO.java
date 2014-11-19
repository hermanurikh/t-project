package ru.tsystems.tproject.DAO.API;

import ru.tsystems.tproject.entities.Contract;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.List;

/**
 * Base CRUD operations along with the specific ones.
 */
public interface ContractDAO extends GenericDAO<Contract, Integer> {

    /**
     * Gets contract by a specified number.
     * @param number of the contract
     * @return a contract
     * @throws CustomDAOException
     */

    public Contract getContractByNumber(long number) throws CustomDAOException;
    public List<Contract> getAllContractsForUser(int id) throws CustomDAOException;

}
