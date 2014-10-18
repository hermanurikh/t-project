package ru.tsystems.tproject.DAO.API;

import ru.tsystems.tproject.entities.Role;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.List;

/**
 * Created by german on 18.10.14.
 */
public interface RoleDAO {

    public void create(Role role) throws CustomDAOException;
    public Role read(int id) throws CustomDAOException;
    public void update(Role role) throws CustomDAOException;
    public void delete(Role role) throws CustomDAOException;
    public List<Role> getAllRoles() throws CustomDAOException;
}
