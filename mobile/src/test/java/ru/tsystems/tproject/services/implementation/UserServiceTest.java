package ru.tsystems.tproject.services.implementation;


import org.junit.Test;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.jdbc.JdbcTestUtils;
import ru.tsystems.tproject.DAO.API.ContractDAO;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.services.API.ContractService;
import ru.tsystems.tproject.services.API.RoleService;
import ru.tsystems.tproject.services.API.UserService;

import java.util.Date;

import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = "/spring.xml")
public class UserServiceTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private UserService userService;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String createScript = "mobile/src/main/resources/sql/create-data.sql";
    private final String deleteScript = "mobile/src/main/resources/sql/remove-data.sql";

    @Before
    public void insertData() {
        JdbcTestUtils.executeSqlScript(jdbcTemplate, new FileSystemResource(createScript), false);
    }
    @After
    public void removeData() {
        JdbcTestUtils.executeSqlScript(jdbcTemplate, new FileSystemResource(deleteScript), false);

    }

    @Test
    public void testUserGet() {
        User user2 = userService.getUserByLogin("jameslogin");
        assertTrue(user2.getRole().getId() == 2);
    }

}
