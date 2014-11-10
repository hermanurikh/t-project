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
    /*
    private static final String createScript = "mobile/src/main/resources/sql/create-data-contract.sql";
    private static final String deleteScript = "mobile/src/main/resources/sql/remove-data-contract.sql";
     */
    private static final String createScript = "src/main/resources/sql/create-data-contract.sql";
    private static final String deleteScript = "src/main/resources/sql/remove-data-contract.sql";


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
        int a = contractService.getAllContracts().size();
        Contract contract = new Contract(9999999999l, userService.getUserById(299999998), tariffService.getTariffById(211369877));
        contractService.createContract(contract);
        int b = contractService.getAllContracts().size();
        assertTrue(b == a + 1);
        contractService.deleteContract(contract);
    }
    //a test to check the "read" method
    @Test
    public void testContractRead() {
        Contract contract = contractService.getContractById(213698745);
        assertTrue(contract.getNumber() == 2030508090);
    }
    //a test to check the "update" method
    @Test
    public void testContractUpdate() {
        Contract contract = contractService.getContractById(213698745);
        assertTrue(contract.getOptions().isEmpty());
        contract.setTariff(tariffService.getTariffById(211369878));
        contract.addOption(optionService.getOptionById(214561783));
        contractService.updateContract(contract);
        contract = contractService.getContractById(contract.getId());
        assertTrue(contract.getTariff().getId() == 211369878);
        assertFalse(contract.getOptions().isEmpty());
        contract.getOptions().clear();
        contractService.updateContract(contract);
    }
    //a test to check the "delete" method
    @Test//(expected = CustomDAOException.class)
    public void testContractDelete() {
        Contract contract = contractService.getContractById(213698745);
        contractService.deleteContract(contract);
        assertNull(contractService.getContractById(213698745));
    }
    //a test to check the "getAllContracts" method
    @Test
    public void testContractGetAll() {
        List<Contract> contractList = contractService.getAllContracts();
        assertTrue(contractList.size() > 1);
    }
    //a test to check the "getContractByNumber" method
    @Test
    public void testContractGetByNumber() {
        Contract contract = contractService.getContractByNumber(2030508090);
        assertNotNull(contract);
    }
    //a test to check the "getUserByNumber" method in UserService
    @Test
    public void testUserGetByNumber() {
        User user = userService.getUserByNumber(2030508091);
        assertNotNull(user);
    }







}
