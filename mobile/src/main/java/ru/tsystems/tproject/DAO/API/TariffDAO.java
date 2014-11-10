package ru.tsystems.tproject.DAO.API;

import ru.tsystems.tproject.entities.Tariff;
import ru.tsystems.tproject.exceptions.CustomDAOException;

import java.util.List;

/**
 * Base CRUD operations along with the specific ones.
 */
public interface TariffDAO extends GenericDAO<Tariff, Integer> {

}
