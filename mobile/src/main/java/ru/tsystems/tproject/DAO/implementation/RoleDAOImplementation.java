package ru.tsystems.tproject.DAO.implementation;

import org.springframework.stereotype.Repository;
import ru.tsystems.tproject.DAO.API.RoleDAO;
import ru.tsystems.tproject.entities.Role;

/**
 * An implementation of RoleDAO API.
 */
@Repository("roleDAO")
public class RoleDAOImplementation extends GenericDAOImplementation<Role, Integer> implements RoleDAO{


}
