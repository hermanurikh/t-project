package ru.tsystems.tproject.DAO.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.tsystems.tproject.DAO.API.UserDAO;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * An implementation of a UserDAO API.
 */
@Repository("userDAO")
public class UserDAOImplementation implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(User entity) throws CustomDAOException {
        try {
            entityManager.persist(entity);
        }
        catch (PersistenceException ex)
        {
            throw new CustomDAOException("Entity not created: " + entity, ex);
        }
    }

    @Override
    public User read(Integer id) throws CustomDAOException {
        try {
            return entityManager.find(User.class, id);
        }
        catch (PersistenceException ex) {
            throw new CustomDAOException("Entity with id " + id + " not found", ex);
        }
    }

    @Override
    public void update(User entity) throws CustomDAOException {
        try {
            entityManager.merge(entity);
        }
        catch (PersistenceException ex) {
            throw new CustomDAOException("Entity not updated: " + entity, ex);
        }

    }

    @Override
    public void delete(User entity) throws CustomDAOException {
        try {
            entityManager.remove(entityManager.merge(entity));
        }
        catch (PersistenceException ex) {
            throw new CustomDAOException("Entity not deleted: " + entity, ex);
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
            System.out.println("em exists = " + (this.entityManager != null));
            Query query = entityManager.createQuery("select u from User u where u.login=:login").setParameter("login", login);
            return (User) query.getSingleResult();
        }
        catch (PersistenceException ex) {
            throw new CustomDAOException("User with login " + login + " not found!", ex);
        }

    }


}
