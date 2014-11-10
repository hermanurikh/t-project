package ru.tsystems.tproject.DAO.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.tsystems.tproject.DAO.API.TariffDAO;
import ru.tsystems.tproject.entities.Tariff;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 * An implementation of TariffDAO API.
 */
@Repository("tariffDAO")
public class TariffDAOImplementation extends GenericDAOImplementation<Tariff, Integer> implements TariffDAO {


}
