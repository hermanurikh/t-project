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
import ru.tsystems.tproject.exceptions.CustomDAOException;
import ru.tsystems.tproject.services.API.ContractService;
import ru.tsystems.tproject.services.API.OptionService;
import ru.tsystems.tproject.services.API.RoleService;
import ru.tsystems.tproject.services.API.UserService;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = "/spring.xml")
public class UserServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;


    private final String createScript = "mobile/src/main/resources/sql/create-data.sql";
    private final String deleteScript = "mobile/src/main/resources/sql/remove-data.sql";
/*
    @Before
    public void insertData() {
        JdbcTestUtils.executeSqlScript(jdbcTemplate, new FileSystemResource(createScript), false);
    }
    @After
    public void removeData() {
        JdbcTestUtils.executeSqlScript(jdbcTemplate, new FileSystemResource(deleteScript), false);

    }
*/
    @Test
    public void testUserCreateReadUpdate() {
        //create test
        userService.createUser(new User("James", "Brown", new Date(), "passport", "address", "email@gmail.com", "jameslogin2", 300, "password", roleService.getRoleById(2)));
        userService.createUser(new User("James", "Brown", new Date(), "passport", "address", "email@gmail.com", "jameslogin", 300, "password", roleService.getRoleById(2)));
        User user = userService.getUserByLogin("jameslogin");
        //read by login test
        assertTrue(user.getRole().getId() == 2);
        User user2 = userService.getUserById(user.getId());
        //read by id test
        assertTrue(user2.getName().equals(user.getName()));

        user2.setBalance(1398);
        userService.updateUser(user2);
        user = userService.getUserByLogin("jameslogin");
        //update test
        assertTrue(user.getBalance() == 1398);
        List<User> userList = userService.getAllUsers();
        //getAllUsers test
        assertTrue(userList.size() > 1);
    }
    @Test(expected = CustomDAOException.class)
    public void testUserDelete() {
        //delete test
        User user = userService.getUserByLogin("jameslogin");
        userService.deleteUser(user);
        user = userService.getUserByLogin("jameslogin2");
        userService.deleteUser(user);
        userService.getUserByLogin("jameslogin");

    }


}
