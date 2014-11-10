package ru.tsystems.tproject.services.API;

import ru.tsystems.tproject.entities.Contract;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.List;

/**
 * CRUD methods along with the specific ones needed for this class.
 */
public interface ContractService extends GenericService<Contract, Integer> {


    /**
     * A method to get contract by number.
     * @param number
     * @return
     * @throws CustomDAOException
     */

    public Contract getContractByNumber(long number) throws CustomDAOException;

}
