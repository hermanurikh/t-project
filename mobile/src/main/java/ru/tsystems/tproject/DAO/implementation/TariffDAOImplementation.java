package ru.tsystems.tproject.DAO.implementation;

import org.springframework.stereotype.Repository;
import ru.tsystems.tproject.DAO.API.TariffDAO;
import ru.tsystems.tproject.entities.Tariff;

/**
 * An implementation of TariffDAO API.
 */
@Repository("tariffDAO")
public class TariffDAOImplementation extends GenericDAOImplementation<Tariff, Integer> implements TariffDAO {


}
