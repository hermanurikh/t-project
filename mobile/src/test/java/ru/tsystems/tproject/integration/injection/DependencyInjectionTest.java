package ru.tsystems.tproject.integration.injection;

import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import ru.tsystems.tproject.DAO.API.*;
import ru.tsystems.tproject.services.API.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * A test of entity manager injection.
 */
@ContextConfiguration(locations = "/spring.xml")
public class DependencyInjectionTest extends AbstractJUnit4SpringContextTests {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private UserService userService;
    @Autowired
    private TariffService tariffService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private OptionService optionService;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private TariffDAO tariffDAO;
    @Autowired
    private ContractDAO contractDAO;
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private OptionDAO optionDAO;



    @Test
    public void testEntityManager() {
        assertNotNull(entityManager);
    }
    @Test
    public void testUserService() {

        assertNotNull(userService);
    }
    @Test
    public void testTariffService() {
        assertNotNull(tariffService);

    }
    @Test
    public void testContractService() {
        assertNotNull(contractService);

    }
    @Test
    public void testRoleService() {
        assertNotNull(roleService);

    }
    @Test
    public void testOptionService() {
        assertNotNull(optionService);

    }
    @Test
    public void testUserDAO() {
        assertNotNull(userDAO);
    }
    @Test
    public void testTariffDAO() {
        assertNotNull(tariffDAO);
    }
    @Test
    public void testContractDAO() {
        assertNotNull(contractDAO);
    }
    @Test
    public void testRoleDAO() {
        assertNotNull(roleDAO);
    }
    @Test
    public void testOptionDAO() {
        assertNotNull(optionDAO);
    }
}
