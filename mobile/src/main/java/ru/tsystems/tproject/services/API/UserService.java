package ru.tsystems.tproject.services.API;

import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.List;

/**
 * Created by german on 19.10.14.
 */
public interface UserService {

    public void createUser(User user) throws CustomDAOException;
    public User getUserById(int id) throws CustomDAOException;
    public void updateUser(User user) throws CustomDAOException;
    public void deleteUser(User user) throws CustomDAOException;
    public List<User> getAllUsers() throws CustomDAOException;
    public User getUserByNumber(long number) throws CustomDAOException;

}
