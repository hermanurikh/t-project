package ru.tsystems.tproject.DAO.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.tsystems.tproject.DAO.API.RoleDAO;
import ru.tsystems.tproject.entities.Role;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * An implementation of RoleDAO API.
 */
@Repository("roleDAO")
public class RoleDAOImplementation implements RoleDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Role role) throws CustomDAOException {
        try{
            entityManager.persist(role);
        }
        catch (PersistenceException ex)
        {
            throw new CustomDAOException("Role not created: " + role, ex);
        }

    }

    @Override
    public Role read(int id) throws CustomDAOException {
        try{
            return entityManager.find(Role.class, id);
        }
        catch (PersistenceException ex)
        {
            throw new CustomDAOException("Role not read: " + id, ex);
        }
    }

    @Override
    public void update(Role role) throws CustomDAOException {
        try{
            entityManager.merge(role);
        }
        catch(PersistenceException ex)
        {
            throw new CustomDAOException("Role not updated: " + role, ex);
        }

    }

    @Override
    public void delete(Role role) throws CustomDAOException {
        try{
            entityManager.remove(role);
        }
        catch (PersistenceException ex)
        {
            throw new CustomDAOException("Role not deleted: " + role, ex);
        }

    }

    /**
     * Lists all possible roles.
     * @return a list of roles
     * @throws CustomDAOException
     */
    @Override
    public List<Role> getAllRoles() throws CustomDAOException {
        try{
            return entityManager.createNamedQuery("Role.getAllRoles", Role.class).getResultList();
        }
        catch (PersistenceException ex)
        {
            throw new CustomDAOException("Roles not got", ex);
        }
    }
}
