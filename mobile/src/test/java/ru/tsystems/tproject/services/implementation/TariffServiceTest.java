package ru.tsystems.tproject.services.implementation;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.jdbc.JdbcTestUtils;
import ru.tsystems.tproject.entities.Tariff;
import ru.tsystems.tproject.services.API.OptionService;
import ru.tsystems.tproject.services.API.TariffService;

import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration(locations = "/spring.xml")
public class TariffServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private TariffService tariffService;
    @Autowired
    private OptionService optionService;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /*for testing in IDEA uncomment the variables below */

    /*private static final String CREATE_SCRIPT = "mobile/src/main/resources/sql/create-data-tariff.sql";
    private static final String DELETE_SCRIPT = "mobile/src/main/resources/sql/remove-data-tariff.sql";*/

    private static final String CREATE_SCRIPT = "src/main/resources/sql/create-data-tariff.sql";
    private static final String DELETE_SCRIPT = "src/main/resources/sql/remove-data-tariff.sql";

    @Before
    public void insertData() {
        //noinspection deprecation
        JdbcTestUtils.executeSqlScript(jdbcTemplate, new FileSystemResource(CREATE_SCRIPT), false);
    }
    @After
    public void deleteData() {
        //noinspection deprecation
        JdbcTestUtils.executeSqlScript(jdbcTemplate, new FileSystemResource(DELETE_SCRIPT), false);
    }


    //a test to check the "create" method
    @Test
    public void testTariffCreate() {
        int a = tariffService.getAll().size();
        Tariff tariff = new Tariff("testTariff3", 1234);
        tariffService.createEntity(tariff);
        int b = tariffService.getAll().size();
        assertTrue(a == b - 1);
        tariffService.deleteEntity(tariff);
    }
    //a test to check the "read" method
    @Test
    public void testTariffRead() {
        Tariff tariff = tariffService.getEntityById(211369877);
        assertEquals(tariff.getName(), "testTariff1");
    }
    //a test to check the "update" method

    @Test
    public void testTariffUpdate() {
        Tariff tariff = tariffService.getEntityById(211369877);
        assertTrue(tariff.getPossibleOptions().isEmpty());
        tariff.setPrice(89765);
        tariff.getPossibleOptions().add(optionService.getEntityById(214561786));
        tariffService.updateEntity(tariff);
        tariff = tariffService.getEntityById(211369877);
        assertFalse(tariff.getPossibleOptions().isEmpty());
        // a test of getAllOptionsForTariff
        assertTrue(tariff.getPossibleOptions().size() == optionService.getAllOptionsForTariff(tariff.getId()).size());
        assertTrue(tariff.getPrice() == 89765);
        tariff.removePossibleOptions();
        tariffService.updateEntity(tariff);

    }
    //a test to check the "delete" method
    @Test//expected = CustomDAOException.class)
    public void testTariffDelete() {
        Tariff tariff = tariffService.getEntityById(211369877);
        tariffService.deleteEntity(tariff);
        assertNull(tariffService.getEntityById(211369877));
    }
    //a test to check the "getAll" method
    @Test
    public void testTariffGetAll() {
        List<Tariff> tariffList = tariffService.getAll();
        assertTrue(tariffList.size() > 1);
    }






}
