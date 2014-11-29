package ru.tsystems.tproject.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.tproject.DAO.API.UserDAO;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.exceptions.CustomDAOException;
import ru.tsystems.tproject.exceptions.UserNotFoundException;
import ru.tsystems.tproject.services.API.UserService;

import java.util.List;

/**
 * A UserService API implementation,
 */
@Service("userService")
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserDAO userDAO;

    /**
     * The implementation of create entity API.
     * @param entity user;
     * @throws CustomDAOException
     */
    @Override
    @Transactional
    public void createEntity(User entity) throws CustomDAOException {
        this.userDAO.create(entity);
    }

    /**
     * The implementation of read entity API.
     * @param id the id of the user;
     * @return the user;
     * @throws CustomDAOException
     */
    @Override
    @Transactional
    public User getEntityById(Integer id) throws CustomDAOException {
        return this.userDAO.read(id);
    }

    /**
     * The implementation of update entity API.
     * @param entity the user;
     * @throws CustomDAOException
     */
    @Override
    @Transactional
    public void updateEntity(User entity) throws CustomDAOException {
        this.userDAO.update(entity);
    }

    /**
     * The implementation of delete entity API.
     * @param entity the user;
     * @throws CustomDAOException
     */
    @Override
    @Transactional
    public void deleteEntity(User entity) throws CustomDAOException {
        this.userDAO.delete(entity);
    }

    /**
     * Gets all users from the database.
     * @return a list of users
     * @throws CustomDAOException
     */
    @Override
    @Transactional
    public List<User> getAll() throws CustomDAOException {
        return this.userDAO.getAll();

    }

    /**
     * Gets user by a specified number.
     * @param number the number of user's contract
     * @return a user
     * @throws CustomDAOException
     */
    @Override
    @Transactional
    public User getUserByNumber(long number) throws UserNotFoundException {
        return this.userDAO.getUserByNumber(number);
    }

    /**
     * Get user by a specified login,
     * @param login the user's login
     * @return a user
     * @throws CustomDAOException
     */
    @Override
    @Transactional
    public User getUserByLogin(String login) throws UserNotFoundException {
        return this.userDAO.getUserByLogin(login);
    }


}
