package ru.tsystems.tproject.DAO.API;

import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.List;

/**
 * Created by german on 18.10.14.
 */
public interface UserDAO {
    public void create(User user) throws CustomDAOException;

    public User read(int id) throws CustomDAOException;

    public void update(User user) throws CustomDAOException;

    public void delete(User user) throws CustomDAOException;

    public User getUserByNumber(long number) throws CustomDAOException;

    public List<User> getAllUsers() throws CustomDAOException;
}
