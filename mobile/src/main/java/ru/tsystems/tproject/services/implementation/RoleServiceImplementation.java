package ru.tsystems.tproject.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.tproject.DAO.API.RoleDAO;
import ru.tsystems.tproject.entities.Role;
import ru.tsystems.tproject.exceptions.CustomDAOException;
import ru.tsystems.tproject.services.API.RoleService;

import java.util.List;

/**
 * An implementation of RoleService API.
 */
@Service("roleService")
public class RoleServiceImplementation  implements RoleService {
    @Autowired
    private RoleDAO roleDAO;

    /**
     * The implementation of create entity API.
     * @param role role
     * @throws CustomDAOException
     */
    @Override
    @Transactional
    public void createEntity(Role role) throws CustomDAOException {
        this.roleDAO.create(role);
    }

    /**
     * The implementation of read entity API.
     * @param id the id of the entity;
     * @return role
     * @throws CustomDAOException
     */

    @Override
    @Transactional
    public Role getEntityById(Integer id) throws CustomDAOException {
        return this.roleDAO.read(id);
    }

    /**
     * The implementation of update entity API.
     * @param role role
     * @throws CustomDAOException
     */
    @Override
    @Transactional
    public void updateEntity(Role role) throws CustomDAOException {
        this.roleDAO.update(role);
    }

    /**
     * The implementation of delete entity API.
     * @param role role
     * @throws CustomDAOException
     */
    @Override
    @Transactional
    public void deleteEntity(Role role) throws CustomDAOException {
        this.roleDAO.delete(role);

    }

    /**
     * A method to get all possible roles.
     * @return a list of roles
     * @throws CustomDAOException
     */
    @Override
    @Transactional
    public List<Role> getAll() throws CustomDAOException {
        return this.roleDAO.getAll();
    }
}


