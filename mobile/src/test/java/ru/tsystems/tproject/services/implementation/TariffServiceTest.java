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
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.exceptions.CustomDAOException;
import ru.tsystems.tproject.services.API.OptionService;
import ru.tsystems.tproject.services.API.RoleService;
import ru.tsystems.tproject.services.API.TariffService;
import ru.tsystems.tproject.services.API.UserService;

import java.util.Date;
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
    private final String createScript = "mobile/src/main/resources/sql/create-data-tariff.sql";
    private final String deleteScript = "mobile/src/main/resources/sql/remove-data-tariff.sql";

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
    public void testTariffCreate() {
        int a = tariffService.getAllTariffs().size();
        Tariff tariff = new Tariff("testTariff3", 1234);
        tariffService.createTariff(tariff);
        int b = tariffService.getAllTariffs().size();
        assertTrue(a == b - 1);
        tariffService.deleteTariff(tariff);
    }
    //a test to check the "read" method
    @Test
    public void testTariffRead() {
        Tariff tariff = tariffService.getTariffById(211369877);
        assertEquals(tariff.getName(), "testTariff1");
    }
    //a test to check the "update" method

    @Test
    public void testTariffUpdate() {
        Tariff tariff = tariffService.getTariffById(211369877);
        assertTrue(tariff.getPossibleOptions().isEmpty());
        tariff.setPrice(89765);
        tariff.getPossibleOptions().add(optionService.getOptionById(214561786));
        tariffService.updateTariff(tariff);
        tariff = tariffService.getTariffById(211369877);
        assertFalse(tariff.getPossibleOptions().isEmpty());
        assertTrue(tariff.getPrice() == 89765);
        tariff.removePossibleOptions();
        tariffService.updateTariff(tariff);

    }
    //a test to check the "delete" method
    @Test//expected = CustomDAOException.class)
    public void testTariffDelete() {
        Tariff tariff = tariffService.getTariffById(211369877);
        tariffService.deleteTariff(tariff);
        assertNull(tariffService.getTariffById(211369877));
    }
    //a test to check the "getAllTariffs" method
    @Test
    public void testTariffGetAll() {
        List<Tariff> tariffList = tariffService.getAllTariffs();
        assertTrue(tariffList.size() > 1);
    }






}
