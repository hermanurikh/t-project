package ru.tsystems.tproject.services.API;

import ru.tsystems.tproject.entities.Role;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.List;

/**
 * Created by german on 19.10.14.
 */
public interface RoleService {
    public void createRole(Role role) throws CustomDAOException;
    public Role getRoleById(int id) throws CustomDAOException;
    public void updateRole(Role role) throws CustomDAOException;
    public void deleteRole(Role role) throws CustomDAOException;
    public List<Role> getAllRoles() throws CustomDAOException;
}
