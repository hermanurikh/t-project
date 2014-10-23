package ru.tsystems.tproject.services.implementation;

import ru.tsystems.tproject.DAO.API.UserDAO;
import ru.tsystems.tproject.DAO.implementation.UserDAOImplementation;
import ru.tsystems.tproject.entities.Manager;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.exceptions.CustomDAOException;
import ru.tsystems.tproject.services.API.UserService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by german on 19.10.14.
 */
public class UserServiceImplementation implements UserService {
    private EntityManager entityManager = Manager.getEntityManager();
    private UserDAO userDAO = new UserDAOImplementation(entityManager);

    public void createUser(User user) throws CustomDAOException {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            userDAO.create(user);
            entityTransaction.commit();
        }
        catch (RuntimeException ex)
        {
            if (entityTransaction.isActive()) {
            entityTransaction.rollback();
        }
            throw new CustomDAOException("Unable to create user: " + user, ex);
        }

    }

    @Override
    public User getUserById(int id) throws CustomDAOException {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            User user = userDAO.read(id);
            entityTransaction.commit();
            if (user == null) throw new CustomDAOException("User with id " + id + " not found");
            else return user;
        }
        catch (RuntimeException ex)
        {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new CustomDAOException("Unable to get user: " + id, ex);
        }
    }

    @Override
    public void updateUser(User user) throws CustomDAOException {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            userDAO.update(user);
            entityTransaction.commit();
        }
        catch (RuntimeException ex)
        {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new CustomDAOException("Unable to update user: " + user, ex);
        }

    }

    @Override
    public void deleteUser(User user) throws CustomDAOException {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            userDAO.delete(user);
            entityTransaction.commit();
        }
        catch (RuntimeException ex)
        {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new CustomDAOException("Unable to delete user: " + user, ex);
        }
    }

    @Override
    public List<User> getAllUsers() throws CustomDAOException {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            List<User> list = userDAO.getAllUsers();
            entityTransaction.commit();
            return list;
        }
        catch (RuntimeException ex)
        {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new CustomDAOException("Unable to get all users", ex);
        }

    }

    @Override
    public User getUserByNumber(long number) throws CustomDAOException {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            User user = userDAO.getUserByNumber(number);
            entityTransaction.commit();
            if (user == null) throw new CustomDAOException("User with number " + number + " not found");
            else return user;
        }
        catch (RuntimeException ex)
        {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new CustomDAOException("Unable to get user with number: " + number, ex);
        }
    }


    @Override
    public User getUserByLogin(String login) throws CustomDAOException {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            User user = userDAO.getUserByLogin(login);
            entityTransaction.commit();
            if (user == null) throw new CustomDAOException("User with login " + login + " not found");
            else return user;
        }
        catch (RuntimeException ex)
        {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new CustomDAOException("Unable to get user with login: " + login, ex);
        }
    }
}
