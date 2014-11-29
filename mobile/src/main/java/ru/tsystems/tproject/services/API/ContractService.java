package ru.tsystems.tproject.services.API;

import ru.tsystems.tproject.entities.Contract;
import ru.tsystems.tproject.exceptions.ContractNotFoundException;
import ru.tsystems.tproject.exceptions.ContractsForEntityNotGotException;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.List;

/**
 * CRUD methods along with the specific ones needed for this class.
 */
public interface ContractService extends GenericService<Contract, Integer> {


    /**
     * A method to get contract by number.
     * @param number the number of the contract
     * @return a contract
     * @throws CustomDAOException
     */

    public Contract getContractByNumber(long number) throws ContractNotFoundException;

    /**
     * A method to get a list of all contracts of a user.
     * @param id the user's id;
     * @return a list of contracts
     * @throws ContractsForEntityNotGotException
     */
    public List<Contract> getAllContractsForUser(int id) throws ContractsForEntityNotGotException;

}
