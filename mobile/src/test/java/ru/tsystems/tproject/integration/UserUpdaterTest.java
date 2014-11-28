package ru.tsystems.tproject.integration;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.tsystems.tproject.entities.Role;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.services.API.RoleService;
import ru.tsystems.tproject.services.API.UserService;
import ru.tsystems.tproject.utils.Converter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * A class to test the UserUpdater class.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserUpdaterTest {
    @InjectMocks
    private UserUpdater userUpdater;
    @Mock
    private UserService userService;
    @Mock
    private RoleService roleService;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String DATE = "1992-04-14";
    private static final String PASSPORT = "passport";
    private static final String ADDRESS = "address";
    private static final String EMAIL = "email";
    private static final String LOGIN = "login";
    private static final Integer BALANCE = 0;
    private static final Integer BALANCE_NOT_NULL = 5000;
    private static final String PASSWORD = "password";
    private static final String ROLE = "User";
    private static final String INCORRECT =
            "ahdjfhjdfhdjfhdjfhdjfhjdhfjdhfjhfjhdjfhjdfhdjfjdfjd" +
            "hfahdjfhjdfhdjfhdjfhdjfhjdhfjdhfjhfjhdjfhjdfhdjfjdf" +
             "jdhfjdhjfhjdhjfhahdjfhjdfhdjfhdjfhdjfhjdhfjdhfjgjk" +
            "hfjhdjfhjdfhdjfjdfjdhfjdhjfhahdjfhjdfhdjfhdjfhdjfhj";
    private static final String EMAIL_INCORRECT =
            "kjfiuriotpsldkfjtusjdfjfhsurhfjkrlfgasdnvnmajhasdfj";
    private static final Integer BALANCE_INCORRECT = 100001;



    @Before
    public void init() throws Exception{
        when(roleService.getEntityById(1)).thenReturn(new Role("User"));
        when(userService.getEntityById(1)).thenReturn(
                new User(null, null, new Date(), null, null, null, LOGIN, 0, null, new Role(ROLE))
        );

    }
    @Test
    public void createUserSuccess() throws Exception{
        User user2 = new User(NAME, SURNAME, DATE_FORMAT.parse(DATE), PASSPORT, ADDRESS, EMAIL, LOGIN, BALANCE, Converter.getMD5(PASSWORD), new Role(ROLE));
        User user = userUpdater.createUser(NAME, SURNAME, DATE, PASSPORT, ADDRESS, EMAIL, BALANCE, LOGIN, PASSWORD, 1);
        assertEquals(user.toString(), user2.toString());
        user = userUpdater.createUser(NAME, SURNAME, DATE, PASSPORT, ADDRESS, EMAIL, null, LOGIN, PASSWORD, 1);
        assertEquals(user.toString(), user2.toString());
        user2 = new User(NAME, SURNAME, DATE_FORMAT.parse(DATE), null, null, null, LOGIN, BALANCE, Converter.getMD5(PASSWORD), new Role(ROLE));
        user = userUpdater.createUser(NAME, SURNAME, DATE, null, null, null, null, LOGIN, PASSWORD, 1);
        assertEquals(user.toString(), user2.toString());
        user2 = new User(NAME, SURNAME, DATE_FORMAT.parse(DATE), null, null, null, LOGIN, BALANCE_NOT_NULL, Converter.getMD5(PASSWORD), new Role(ROLE));
        user = userUpdater.createUser(NAME, SURNAME, DATE, null, null, null, BALANCE_NOT_NULL, LOGIN, PASSWORD, 1);
        assertEquals(user.toString(), user2.toString());
    }
    @Test (expected = IOException.class)
    public void createUserFailPassport() throws Exception {
        userUpdater.createUser(NAME, SURNAME, DATE, INCORRECT, ADDRESS, EMAIL, BALANCE, LOGIN, PASSWORD, 1);
    }
    @Test (expected = IOException.class)
    public void createUserFailAddress() throws Exception {
        userUpdater.createUser(NAME, SURNAME, DATE, PASSPORT, INCORRECT, EMAIL, BALANCE, LOGIN, PASSWORD, 1);
    }
    @Test (expected = IOException.class)
    public void createUserFailEmail() throws Exception {
        userUpdater.createUser(NAME, SURNAME, DATE, PASSPORT, ADDRESS, EMAIL_INCORRECT, BALANCE, LOGIN, PASSWORD, 1);
    }
    @Test (expected = IOException.class)
    public void createUserFailBalance() throws Exception {
        userUpdater.createUser(NAME, SURNAME, DATE, PASSPORT, ADDRESS, EMAIL, BALANCE_INCORRECT, LOGIN, PASSWORD, 1);
    }
    @Test
    public void updateUserSuccess() throws Exception{
        User user2 = new User(NAME, SURNAME, DATE_FORMAT.parse(DATE), PASSPORT, ADDRESS, EMAIL, LOGIN, BALANCE, Converter.getMD5(PASSWORD), new Role(ROLE));
        assertNotEquals(userService.getEntityById(1), user2);
        User user = userUpdater.updateUser(1, NAME, SURNAME, DATE, PASSPORT, ADDRESS, EMAIL, BALANCE, PASSWORD);
        assertEquals(user.toString(), user2.toString());
    }
    @Test(expected = IOException.class)
    public void updateUserFailPassport() throws Exception {
        userUpdater.updateUser(1, NAME, SURNAME, DATE, INCORRECT, ADDRESS, EMAIL, BALANCE, PASSWORD);
    }
    @Test(expected = IOException.class)
    public void updateUserFailAddress() throws Exception {
        userUpdater.updateUser(1, NAME, SURNAME, DATE, PASSPORT, INCORRECT, EMAIL, BALANCE, PASSWORD);
    }
    @Test(expected = IOException.class)
    public void updateUserFailEmail() throws Exception {
        userUpdater.updateUser(1, NAME, SURNAME, DATE, PASSPORT, ADDRESS, EMAIL_INCORRECT, BALANCE, PASSWORD);
    }
    @Test(expected = IOException.class)
    public void updateUserFailBalance() throws Exception {
        userUpdater.updateUser(1, NAME, SURNAME, DATE, PASSPORT, ADDRESS, EMAIL, BALANCE_INCORRECT, PASSWORD);
    }

}
