package ru.tsystems.tproject.DAO.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.tsystems.tproject.DAO.API.RoleDAO;
import ru.tsystems.tproject.entities.Role;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * An implementation of RoleDAO API.
 */
@Repository
public class RoleDAOImplementation implements RoleDAO{
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) { this.sessionFactory = sessionFactory; }

    @Override
    public void create(Role role) throws CustomDAOException {
        try{
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(role);
        }
        catch (PersistenceException ex)
        {
           throw new CustomDAOException("Role not created: " + role, ex);
        }

    }

    @Override
    public Role read(int id) throws CustomDAOException {
        try{
            Session session = this.sessionFactory.getCurrentSession();
            return (Role) session.load(Role.class, id);
        }
        catch (PersistenceException ex)
        {
            throw new CustomDAOException("Role not read: " + id, ex);
        }
    }

    @Override
    public void update(Role role) throws CustomDAOException {
        try{
            Session session = this.sessionFactory.getCurrentSession();
            session.update(role);
        }
        catch(PersistenceException ex)
        {
            throw new CustomDAOException("Role not updated: " + role, ex);
        }

    }

    @Override
    public void delete(Role role) throws CustomDAOException {
        try{
            Session session = this.sessionFactory.getCurrentSession();
            session.delete(role);
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
    @SuppressWarnings("unchecked")
    @Override
    public List<Role> getAllRoles() throws CustomDAOException {
        try{
            Session session = this.sessionFactory.getCurrentSession();
            return (List<Role>) session.createQuery("SELECT r FROM Role r").list();
        }
        catch (PersistenceException ex)
        {
            throw new CustomDAOException("Roles not got", ex);
        }
    }
}
