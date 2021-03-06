package ru.tsystems.tproject.DAO.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.tsystems.tproject.DAO.API.UserDAO;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.exceptions.CustomDAOException;
import ru.tsystems.tproject.exceptions.UserNotFoundException;

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
public class UserDAOImplementation extends GenericDAOImplementation<User, Integer> implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;


    /**
     * Gets user by a specified number.
     * @param number - a user contract's number
     * @return user
     * @throws CustomDAOException
     */
    @Override
    public User getUserByNumber(long number) throws UserNotFoundException {
        try {
            Query query = entityManager.createQuery("select c.user from Contract c where c.number=:number").setParameter("number", number);
            return (User) query.getSingleResult();
        }
        catch (PersistenceException ex) {
            throw new UserNotFoundException("User with number " + number + " not found", ex);
        }

    }

    /**
     * Gets user by a specified login.
     * @param login - a login of the user
     * @return user
     * @throws CustomDAOException
     */
   @Override
    public User getUserByLogin(String login) throws UserNotFoundException {
        try {
            Query query = entityManager.createQuery("select u from User u where u.login=:login").setParameter("login", login);
            return (User) query.getSingleResult();
        }
        catch (PersistenceException ex) {
            throw new UserNotFoundException("User with login " + login + " not found!", ex);
        }

    }


}
