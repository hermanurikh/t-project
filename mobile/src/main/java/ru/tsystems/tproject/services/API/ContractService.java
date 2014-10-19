package ru.tsystems.tproject.services.API;

import ru.tsystems.tproject.entities.Contract;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.List;

/**
 * Created by german on 19.10.14.
 */
public interface ContractService {
    public void createContract(Contract contract) throws CustomDAOException;
    public Contract getContractById(int id) throws CustomDAOException;
    public void updateContract(Contract contract) throws CustomDAOException;
    public void deleteContract(Contract contract) throws CustomDAOException;
    public Contract getContractByNumber(long number) throws CustomDAOException;
    public List<Contract> getAllContracts() throws CustomDAOException;

}
