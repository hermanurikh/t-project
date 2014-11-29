package ru.tsystems.tproject.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.tproject.DAO.API.TariffDAO;
import ru.tsystems.tproject.entities.Tariff;
import ru.tsystems.tproject.exceptions.CustomDAOException;
import ru.tsystems.tproject.services.API.TariffService;

import java.util.List;

/**
 * An implementation of TariffService API.
 */
@Service("tariffService")
public class TariffServiceImplementation implements TariffService {
    @Autowired
    private TariffDAO tariffDAO;

    /**
     * The implementation of create entity API.
     * @param tariff tariff
     * @throws CustomDAOException
     */
    @Override
    @Transactional
    public void createEntity(Tariff tariff) throws CustomDAOException {
        this.tariffDAO.create(tariff);

    }

    /**
     * The implementation of read entity API.
     * @param id the id of the tariff;
     * @return tariff
     * @throws CustomDAOException
     */
    @Override
    @Transactional
    public Tariff getEntityById(Integer id) throws CustomDAOException {
        return this.tariffDAO.read(id);
    }

    /**
     * The implementation of update entity API.
     * @param tariff tariff
     * @throws CustomDAOException
     */
    @Override
    @Transactional
    public void updateEntity(Tariff tariff) throws CustomDAOException {
        this.tariffDAO.update(tariff);

    }

    /**
     * The implementation of delete entity API.
     * @param tariff tariff
     * @throws CustomDAOException
     */
    @Override
    @Transactional
    public void deleteEntity(Tariff tariff) throws CustomDAOException {
        this.tariffDAO.delete(tariff);
    }

    /**
     * A method to get all tariffs from the database.
     * @return a list of tariffs
     * @throws CustomDAOException
     */
    @Override
    @Transactional
    public List<Tariff> getAll() throws CustomDAOException {
        return this.tariffDAO.getAll();

    }
}
