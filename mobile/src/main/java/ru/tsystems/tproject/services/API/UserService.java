package ru.tsystems.tproject.services.API;

import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.exceptions.CustomDAOException;
import ru.tsystems.tproject.exceptions.UserNotFoundException;

/**
 * CRUD along with the specific operations needed.
 */
public interface UserService extends GenericService<User, Integer> {

    /**
     * get a user by specifying its number
     * @param number a user's contract number;
     * @return a user;
     * @throws CustomDAOException
     */
    public User getUserByNumber(long number) throws UserNotFoundException;

    /**
     * get a user by specifying its login
     * @param login a user's login;
     * @return a user;
     * @throws CustomDAOException
     */
    public User getUserByLogin(String login) throws UserNotFoundException;

}
