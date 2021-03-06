package ru.tsystems.tproject.services.implementation;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.tproject.entities.Contract;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.exceptions.CustomDAOException;
import ru.tsystems.tproject.services.API.ContractService;
import ru.tsystems.tproject.services.API.OptionService;
import ru.tsystems.tproject.services.API.TariffService;
import ru.tsystems.tproject.services.API.UserService;

import java.util.List;

import static org.junit.Assert.*;

@SuppressWarnings("deprecation")
@ContextConfiguration(locations = "/spring.xml")
public class ContractServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private ContractService contractService;
    @Autowired
    private UserService userService;
    @Autowired
    private TariffService tariffService;
    @Autowired
    private OptionService optionService;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /*for testing in IDEA uncomment the variables below */

    private static final String createScript = "mobile/src/main/resources/sql/create-data-contract.sql";
    private static final String deleteScript = "mobile/src/main/resources/sql/remove-data-contract.sql";
/*

    private static final String createScript = "src/main/resources/sql/create-data-contract.sql";
    private static final String deleteScript = "src/main/resources/sql/remove-data-contract.sql";
*/


    @Before
    public void insertData() {
        JdbcTestUtils.executeSqlScript(jdbcTemplate, new FileSystemResource(createScript), false);
    }
    @After
    public void deleteData() {
        JdbcTestUtils.executeSqlScript(jdbcTemplate, new FileSystemResource(deleteScript), false);
    }


    //a test to check the "create" method
    @Test
    @Transactional
    @Rollback(true)
    public void testContractCreate() {
        int a = contractService.getAll().size();
        Contract contract = new Contract(9999999999l, userService.getEntityById(299999998), tariffService.getEntityById(211369877));
        contractService.createEntity(contract);
        int b = contractService.getAll().size();
        assertTrue(b == a + 1);
        contractService.deleteEntity(contract);
    }
    //a test to check the "read" method
    @Test
    public void testContractRead() {
        Contract contract = contractService.getEntityById(213698745);
        assertTrue(contract.getNumber() == 2030508090);
    }
    //a test to check the "update" method
    @Test
    public void testContractUpdate() {
        Contract contract = contractService.getEntityById(213698745);
        assertTrue(contract.getOptions().isEmpty());
        contract.setTariff(tariffService.getEntityById(211369878));
        contract.addOption(optionService.getEntityById(214561783));
        contractService.updateEntity(contract);
        contract = contractService.getEntityById(contract.getId());
        assertTrue(contract.getTariff().getId() == 211369878);
        assertFalse(contract.getOptions().isEmpty());
        assertTrue(optionService.getAllOptionsForContract(213698745).size() == 1);
        contract.getOptions().clear();
        contractService.updateEntity(contract);
    }
    //a test to check the "delete" method
    @Test//(expected = CustomDAOException.class)
    public void testContractDelete() {
        Contract contract = contractService.getEntityById(213698745);
        contractService.deleteEntity(contract);
        assertNull(contractService.getEntityById(213698745));
    }
    //a test to check the "getAll" method
    @Test
    public void testContractGetAll() {
        List<Contract> contractList = contractService.getAll();
        assertTrue(contractList.size() > 1);
    }
    //a test to check the "getContractByNumber" method success
    @Test
    public void testContractGetByNumberSuccess() {
        Contract contract = contractService.getContractByNumber(2030508090);
        assertNotNull(contract);
    }
    //a test to check the "getContractByNumber" method failure
    @Test (expected = CustomDAOException.class)
    public void testContractGetByNumberFailure() {
        Contract contract2 = contractService.getContractByNumber(9764839232l);
    }
    //a test to check the "getUserByNumber" method in UserService
    @Test
    public void testUserGetByNumber() {
        User user = userService.getUserByNumber(2030508091);
        assertNotNull(user);
    }
    //a test to check the "getAllContractsForUser" method
    @Test
    public void testGetAllContractsForUser() {
        assertTrue(contractService.getAllContractsForUser(299999998).size() == 2);
    }







}
