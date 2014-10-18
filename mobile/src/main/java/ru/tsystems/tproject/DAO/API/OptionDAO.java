package ru.tsystems.tproject.DAO.API;

import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.List;

/**
 * Created by german on 18.10.14.
 */
public interface OptionDAO {
    public void create(Option option) throws CustomDAOException;
    public Option read(int id) throws CustomDAOException;
    public void update(Option option) throws CustomDAOException;
    public void delete(Option option) throws CustomDAOException;
    public List<Option> getAllOptions() throws CustomDAOException;
    public List<Option> getAllOptionsForTariff() throws CustomDAOException;

}
