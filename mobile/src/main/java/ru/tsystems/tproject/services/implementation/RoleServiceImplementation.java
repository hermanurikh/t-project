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

    @Override
    @Transactional
    public void createEntity(Role role) throws CustomDAOException {
        this.roleDAO.create(role);
    }

    @Override
    @Transactional
    public Role getEntityById(Integer id) throws CustomDAOException {
        return this.roleDAO.read(id);
    }

    @Override
    @Transactional
    public void updateEntity(Role role) throws CustomDAOException {
        this.roleDAO.update(role);
    }

    @Override
    @Transactional
    public void deleteEntity(Role role) throws CustomDAOException {
        this.roleDAO.delete(role);

    }

    /**
     * A method to get all possible roles.
     * @return
     * @throws CustomDAOException
     */
    @Override
    @Transactional
    public List<Role> getAll() throws CustomDAOException {
        return this.roleDAO.getAll();
    }
}


