package ru.tsystems.tproject.DAO.API;

import ru.tsystems.tproject.entities.Role;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.List;

/**
 * Base CRUD operations along with the specific ones.
 */
public interface RoleDAO {

    public void create(Role entity) throws CustomDAOException;

    public Role read(Integer id) throws CustomDAOException;

    public void update(Role entity) throws CustomDAOException;

    public void delete(Role entity) throws CustomDAOException;

    /**
     * Lists all roles.
     * @return a list of roles
     * @throws CustomDAOException
     */
    public List<Role> getAllRoles() throws CustomDAOException;
}
