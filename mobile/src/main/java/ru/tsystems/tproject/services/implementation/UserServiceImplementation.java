package ru.tsystems.tproject.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
 * A UserService API implementation,
 */
@Service("userService")
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserDAO userDAO;


    @Override
    @Transactional
    public void createUser(User user) throws CustomDAOException {
        this.userDAO.create(user);

    }

    @Override
    @Transactional
    public User getUserById(int id) throws CustomDAOException {
        return this.userDAO.read(id);
    }

    @Override
    @Transactional
    public void updateUser(User user) throws CustomDAOException {
        this.userDAO.update(user);

    }

    @Override
    @Transactional
    public void deleteUser(User user) throws CustomDAOException {
        this.userDAO.delete(user);
    }

    /**
     * Gets all users from the database.
     * @return
     * @throws CustomDAOException
     */
    @Override
    @Transactional
    public List<User> getAllUsers() throws CustomDAOException {
        return this.userDAO.getAllUsers();

    }

    /**
     * Gets user by a specified number.
     * @param number
     * @return
     * @throws CustomDAOException
     */
    @Override
    @Transactional
    public User getUserByNumber(long number) throws CustomDAOException {
        return this.userDAO.getUserByNumber(number);
    }

    /**
     * Get user by a specified login,
     * @param login
     * @return
     * @throws CustomDAOException
     */
    @Override
    @Transactional
    public User getUserByLogin(String login) throws CustomDAOException {
        return this.userDAO.getUserByLogin(login);
    }
}
