package ru.tsystems.tproject.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.tproject.DAO.API.UserDAO;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.exceptions.CustomDAOException;
import ru.tsystems.tproject.services.API.UserService;

import java.util.ArrayList;
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
    public void createEntity(User entity) throws CustomDAOException {
        this.userDAO.create(entity);
    }

    @Override
    @Transactional
    public User getEntityById(Integer id) throws CustomDAOException {
        return this.userDAO.read(id);
    }

    @Override
    @Transactional
    public void updateEntity(User entity) throws CustomDAOException {
        this.userDAO.update(entity);
    }

    @Override
    @Transactional
    public void deleteEntity(User entity) throws CustomDAOException {
        this.userDAO.delete(entity);
    }

    /**
     * Gets all users from the database.
     * @return
     * @throws CustomDAOException
     */
    @Override
    @Transactional
    public List<User> getAll() throws CustomDAOException {
        return this.userDAO.getAll();

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
