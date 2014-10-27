package ru.tsystems.tproject.services.API;

import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.List;

/**
 * CRUD along with the specific operations needed.
 */
public interface UserService {

    public void createUser(User user) throws CustomDAOException;
    public User getUserById(int id) throws CustomDAOException;
    public void updateUser(User user) throws CustomDAOException;
    public void deleteUser(User user) throws CustomDAOException;

    /**
     * get all users from the database
     * @return
     * @throws CustomDAOException
     */
    public List<User> getAllUsers() throws CustomDAOException;

    /**
     * get a user by specifying its number
     * @param number
     * @return
     * @throws CustomDAOException
     */
    public User getUserByNumber(long number) throws CustomDAOException;

    /**
     * get a user by specifying its login
     * @param login
     * @return
     * @throws CustomDAOException
     */
    public User getUserByLogin(String login) throws CustomDAOException;

}
