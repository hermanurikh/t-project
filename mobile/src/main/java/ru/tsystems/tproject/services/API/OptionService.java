package ru.tsystems.tproject.services.API;

import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.List;

/**
 * Created by german on 19.10.14.
 */
public interface OptionService {
    public void createOption(Option option) throws CustomDAOException;
    public Option getOptionById(int id) throws CustomDAOException;
    public void updateOption(Option option) throws CustomDAOException;
    public void deleteOption(Option option) throws CustomDAOException;
    public List<Option> getAllOptions() throws CustomDAOException;
    public List<Option> getAllOptionsForTariff(String tariffName) throws CustomDAOException;
}
