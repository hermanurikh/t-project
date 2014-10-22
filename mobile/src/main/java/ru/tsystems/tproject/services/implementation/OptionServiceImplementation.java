package ru.tsystems.tproject.services.implementation;

import ru.tsystems.tproject.DAO.API.OptionDAO;
import ru.tsystems.tproject.DAO.implementation.OptionDAOImplementation;
import ru.tsystems.tproject.entities.Manager;
import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.exceptions.CustomDAOException;
import ru.tsystems.tproject.services.API.OptionService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by german on 19.10.14.
 */
public class OptionServiceImplementation implements OptionService {
    private EntityManager entityManager = Manager.getEntityManager();
    private OptionDAO optionDAO = new OptionDAOImplementation(entityManager);

    @Override
    public void createOption(Option option) throws CustomDAOException {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            optionDAO.create(option);
            entityTransaction.commit();
        }
        catch (RuntimeException ex) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new CustomDAOException("Unable to create option: " + option, ex);
        }
    }

    @Override
    public Option getOptionById(int id) throws CustomDAOException {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Option option = optionDAO.read(id);
            entityTransaction.commit();
            if (option == null) {
                throw new CustomDAOException("Option with id " + id + " not found");
            }
            else return option;
        }
        catch (RuntimeException ex) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new CustomDAOException("Unable to find option with id: " + id, ex);
        }
    }

    @Override
    public void updateOption(Option option) throws CustomDAOException {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            optionDAO.update(option);
            entityTransaction.commit();
        }
        catch (RuntimeException ex) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new CustomDAOException("Unable to update option: " + option, ex);
        }
    }

    @Override
    public void deleteOption(Option option) throws CustomDAOException {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            optionDAO.delete(option);
            entityTransaction.commit();
        }
        catch (RuntimeException ex) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new CustomDAOException("Unable to delete option: " + option, ex);
        }
    }

    @Override
    public List<Option> getAllOptions() throws CustomDAOException {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            List<Option> list = optionDAO.getAllOptions();
            entityTransaction.commit();
            return list;
        }
        catch (RuntimeException ex) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new RuntimeException("Unable to get all opions", ex);
        }
    }

    @Override
    public List<Option> getAllOptionsForTariff(int id) throws CustomDAOException {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            List<Option> list = optionDAO.getAllOptionsForTariff(id);
            entityTransaction.commit();
            return list;
        }
        catch (RuntimeException ex) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new RuntimeException("Unable to get all opions for tariffid " + id, ex);
        }
    }
}
