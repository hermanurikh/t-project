package ru.tsystems.tproject.services.API;

import ru.tsystems.tproject.entities.Role;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.List;

/**
 * Basic operations along with not needed method of getting all roles (just in case).
 */
public interface RoleService {
    public void createRole(Role role) throws CustomDAOException;
    public Role getRoleById(int id) throws CustomDAOException;
    public void updateRole(Role role) throws CustomDAOException;
    public void deleteRole(Role role) throws CustomDAOException;

    /**
     * List all possible roles for users.
     * @return
     * @throws CustomDAOException
     */
    public List<Role> getAllRoles() throws CustomDAOException;
}
