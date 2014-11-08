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
    public void create(Role entity) throws CustomDAOException {
        try {
            entityManager.persist(entity);
        }
        catch (PersistenceException ex)
        {
            throw new CustomDAOException("Entity not created: " + entity, ex);
        }
    }

    @Override
    public Role read(Integer id) throws CustomDAOException {
        try {
            return entityManager.find(Role.class, id);
        }
        catch (PersistenceException ex) {
            throw new CustomDAOException("Entity with id " + id + " not found", ex);
        }
    }

    @Override
    public void update(Role entity) throws CustomDAOException {
        try {
            entityManager.merge(entity);
        }
        catch (PersistenceException ex) {
            throw new CustomDAOException("Entity not updated: " + entity, ex);
        }

    }

    @Override
    public void delete(Role entity) throws CustomDAOException {
        try {
            entityManager.remove(entityManager.merge(entity));
        }
        catch (PersistenceException ex) {
            throw new CustomDAOException("Entity not deleted: " + entity, ex);
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
