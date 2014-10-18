package ru.tsystems.tproject.DAO.API;

import ru.tsystems.tproject.entities.Contract;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.List;

/**
 * Created by german on 18.10.14.
 */
public interface ContractDAO {
    public void create(Contract contract) throws CustomDAOException;

    public Contract read(int id) throws CustomDAOException;

    public void update(Contract contract) throws CustomDAOException;

    public void delete(Contract contract) throws CustomDAOException;

    public Contract getContractByNumber(long number) throws CustomDAOException;

    public List<Contract> getAllContracts() throws CustomDAOException;
}
