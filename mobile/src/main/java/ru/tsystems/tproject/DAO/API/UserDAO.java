package ru.tsystems.tproject.DAO.API;

import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.List;

/**
 * Base CRUD operations along with the specific ones.
 */
public interface UserDAO extends GenericDAO<User, Integer> {

    /**
     * Gets a user by a specified number.
     * @param number of the user's contract
     * @return a user
     * @throws CustomDAOException
     */

    public User getUserByNumber(long number) throws CustomDAOException;

    /**
     * Gets a user by a specified login.
     * @param login of a user
     * @return a user
     * @throws CustomDAOException
     */

    public User getUserByLogin(String login) throws CustomDAOException;

    /**
     * Gets all users from the database.
     * @return a list of all users
     * @throws CustomDAOException
     */

    public List<User> getAllUsers() throws CustomDAOException;
}
