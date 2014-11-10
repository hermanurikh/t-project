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
import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.exceptions.CustomDAOException;
import ru.tsystems.tproject.services.API.OptionService;
import ru.tsystems.tproject.services.API.RoleService;
import ru.tsystems.tproject.services.API.UserService;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration(locations = "/spring.xml")
public class OptionServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private OptionService optionService;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final String createScript = "mobile/src/main/resources/sql/create-data-option.sql";
    private final String deleteScript = "mobile/src/main/resources/sql/remove-data-option.sql";

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
    public void testOptionCreate() {
        int a = optionService.getAllOptions().size();
        Option option = new Option("testOption5", 200, 100);
        optionService.createOption(option);
        int b = optionService.getAllOptions().size();
        assertTrue(b == a + 1);
        optionService.deleteOption(option);
    }
    //a test to check the "read" method
    @Test
    public void testOptionRead() {
        Option option = optionService.getOptionById(214561783);
        assertEquals(option.getName(), "testOption1");
    }
    //a test to check the "update" method
    @Test
    public void testOptionUpdate() {
        Option option = optionService.getOptionById(214561783);
        assertTrue(option.getOptionsTogether().isEmpty());
        assertTrue(option.getOptionsIncompatible().isEmpty());
        option.setInitialPrice(14041992);
        option.addOptionsTogether(optionService.getOptionById(214561784));
        option.addOptionsIncompatible(optionService.getOptionById(214561785));
        optionService.updateOption(option);
        option = optionService.getOptionById(option.getId());
        assertTrue(option.getInitialPrice() == 14041992);
        //a test to check the correct work of options together and options incompatible
        assertFalse(option.getOptionsTogether().isEmpty());
        assertFalse(option.getOptionsIncompatible().isEmpty());
        option.getOptionsTogether().clear();
        option.getOptionsIncompatible().clear();
        optionService.updateOption(option);
    }
    //a test to check the "delete" method
    @Test//(expected = CustomDAOException.class)
    public void testOptionDelete() {
        Option option = optionService.getOptionById(214561783);
        optionService.deleteOption(option);
        assertNull(optionService.getOptionById(214561783));
    }
    //a test to check the "getAllOptions" method
    @Test
    public void testOptionGetAll() {
        List<Option> optionsList = optionService.getAllOptions();
        assertTrue(optionsList.size() > 3);
    }






}