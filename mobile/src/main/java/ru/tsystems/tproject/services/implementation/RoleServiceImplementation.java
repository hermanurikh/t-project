package ru.tsystems.tproject.services.implementation;

import ru.tsystems.tproject.DAO.API.RoleDAO;
import ru.tsystems.tproject.DAO.implementation.RoleDAOImplementation;
import ru.tsystems.tproject.entities.Manager;
import ru.tsystems.tproject.entities.Role;
import ru.tsystems.tproject.exceptions.CustomDAOException;
import ru.tsystems.tproject.services.API.RoleService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * An implementation of RoleService API.
 */
public class RoleServiceImplementation implements RoleService {
    private final EntityManager entityManager = Manager.getEntityManager();
    private final RoleDAO roleDAO = new RoleDAOImplementation(entityManager);

    @Override
    public void createRole(Role role) throws CustomDAOException {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            roleDAO.create(role);
            entityTransaction.commit();
        }
        catch (RuntimeException ex) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new CustomDAOException("Unable to create role: " + role, ex);
        }
    }

    @Override
    public Role getRoleById(int id) throws CustomDAOException {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Role role = roleDAO.read(id);
            entityTransaction.commit();
            if (role == null) throw new CustomDAOException("Role with id " + id + " not found");
            else return role;
        }
        catch (RuntimeException ex) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new CustomDAOException("Unable to create role with id: " + id, ex);
        }
    }

    @Override
    public void updateRole(Role role) throws CustomDAOException {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            roleDAO.update(role);
            entityTransaction.commit();
        }
        catch (RuntimeException ex) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new CustomDAOException("Unable to update role: " + role, ex);
        }

    }

    @Override
    public void deleteRole(Role role) throws CustomDAOException {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            roleDAO.delete(role);
            entityTransaction.commit();
        }
        catch (RuntimeException ex) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new CustomDAOException("Unable to delete role: " + role, ex);
        }

    }

    /**
     * A method to get all possible roles.
     * @return
     * @throws CustomDAOException
     */
    @Override
    public List<Role> getAllRoles() throws CustomDAOException {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            List<Role> list = roleDAO.getAllRoles();
            entityTransaction.commit();
            return list;
        }
        catch (RuntimeException ex) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw new CustomDAOException("Unable to get all roles", ex);
        }
    }
}


