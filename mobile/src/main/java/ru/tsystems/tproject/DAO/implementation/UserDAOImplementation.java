package ru.tsystems.tproject.DAO.implementation;

import ru.tsystems.tproject.DAO.API.UserDAO;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by german on 18.10.14.
 */
public class UserDAOImplementation implements UserDAO {
    EntityManager entityManager;
    public UserDAOImplementation(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public void create(User user) throws CustomDAOException {
        try {
            entityManager.persist(user);
        }
        catch (PersistenceException ex)
        {
            throw new CustomDAOException("User not created: " + user, ex);
        }
    }

    @Override
    public User read(int id) throws CustomDAOException {
        try {
            return entityManager.find(User.class, id);
        }
        catch (PersistenceException ex) {
            throw new CustomDAOException("User with id " + id + " not found", ex);
        }
    }

    @Override
    public void update(User user) throws CustomDAOException {
        try {
            entityManager.merge(user);
        }
        catch (PersistenceException ex) {
            throw new CustomDAOException("User not updated: " + user, ex);
        }

    }

    @Override
    public void delete(User user) throws CustomDAOException {
        try {
            entityManager.remove(user);
        }
        catch (PersistenceException ex) {
            throw new CustomDAOException("User not deleted: " + user, ex);
        }

    }

    @Override
    public List<User> getAllUsers() throws CustomDAOException {
        try {
            return entityManager.createNamedQuery("User.getAllUsers", User.class).getResultList();
        }
        catch (PersistenceException ex) {
            throw new CustomDAOException("Users not got", ex);
        }
    }

    @Override
    public User getUserByNumber(long number) throws CustomDAOException {
        try {
            Query query = entityManager.createQuery("select c.user from Contract c where c.number=:number").setParameter("number", number);
            return (User) query.getSingleResult();
        }
        catch (PersistenceException ex) {
            throw new CustomDAOException("User with number " + number + " not found", ex);
        }

    }

    @Override
    public User getUserByLogin(String login) throws CustomDAOException {
        try {
            Query query = entityManager.createQuery("select u from User u where u.login=:login").setParameter("login", login);
            return (User) query.getSingleResult();
        }
        catch (PersistenceException ex) {
            throw new CustomDAOException("User with login " + login + " not found", ex);
        }

    }


}
