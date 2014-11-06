package ru.tsystems.tproject.DAO.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.tsystems.tproject.DAO.API.UserDAO;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

/**
 * An implementation of a UserDAO API.
 */
@Repository("userDAO")
public class UserDAOImplementation implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

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

    /**
     * Gets all users from the database.
     * @return a list of users
     * @throws CustomDAOException
     */
    @Override
    public List<User> getAllUsers() throws CustomDAOException {
        try {
            return entityManager.createNamedQuery("User.getAllUsers", User.class).getResultList();
        }
        catch (PersistenceException ex) {
            throw new CustomDAOException("Users not got", ex);
        }
    }

    /**
     * Gets user by a specified number.
     * @param number - a user contract's number
     * @return user
     * @throws CustomDAOException
     */
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

    /**
     * Gets user by a specified login.
     * @param login - a login of the user
     * @return user
     * @throws CustomDAOException
     */
    @Override
    public User getUserByLogin(String login) throws CustomDAOException {
        try {
            Query query = entityManager.createQuery("select u from User u where u.login=:login").setParameter("login", login);
            return (User) query.getSingleResult();
        }
        catch (PersistenceException ex) {
            throw new CustomDAOException("User with login " + login + " not found!", ex);
        }

    }


}
