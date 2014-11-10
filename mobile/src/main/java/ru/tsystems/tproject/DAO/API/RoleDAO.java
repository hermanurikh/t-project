package ru.tsystems.tproject.DAO.API;

import ru.tsystems.tproject.entities.Role;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.List;

/**
 * Base CRUD operations along with the specific ones.
 */
public interface RoleDAO extends GenericDAO<Role, Integer> {

    /**
     * Lists all roles.
     * @return a list of roles
     * @throws CustomDAOException
     */
    public List<Role> getAllRoles() throws CustomDAOException;
}
