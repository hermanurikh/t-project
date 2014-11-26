package ru.tsystems.tproject.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.services.API.RoleService;
import ru.tsystems.tproject.services.API.UserService;
import ru.tsystems.tproject.utils.Converter;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * This class handles the data validation and creating/updating the user's data.
 */
@Service("userUpdater")
public class UserUpdater {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * This method validates the entered fields and gives back a user to create.
     * @param name the user's name;
     * @param surname the user's surname;
     * @param date the user's birthday;
     * @param passport the user's passport;
     * @param address the user's address;
     * @param email the user's email;
     * @param balance the user's balance;
     * @param login the user's login;
     * @param password the user's password;
     * @param roleID the user's role, id;
     * @return user
     * @throws Exception
     */
    public User createUser(String name, String surname, String date, String passport, String address, String email,
                           Integer balance, String login, String password, int roleID) throws Exception{

        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setBirthday(dateFormat.parse(date));
        user.setLogin(login);
        user.setPassword(Converter.getMD5(password));
        user.setRole(roleService.getEntityById(roleID));
        if (passport != null && passport.length() !=0) {
            if (passport.length() > 100) {
                throw new IOException("The passport length is too big!");
            } else {
                user.setPassport(passport);
            }
        }
        if (address != null && address.length() !=0) {
            if (address.length() > 100) {
                throw new IOException("The address length is too big!");
            } else {
                user.setAddress(address);
            }
        }
        if (email != null && email.length() !=0) {
            if (email.length() > 50) {
                throw new IOException("The email length is too big!");
            } else {
                user.setEmail(email);
            }
        }
        if (balance != null) {
            if (balance > 100000) {
                throw new IOException("The amount is too high!");
            } else {
                user.setBalance(balance);
            }
        }
        return user;
    }

    /**
     * This method validates the entered fields and gives back a user to update.
     * @param id the user's id;
     * @param name the user's name;
     * @param surname the user's surname;
     * @param date the user's birthday;
     * @param passport the user's passport;
     * @param address the user's address;
     * @param email the user's email;
     * @param balance the user's balance;
     * @param password the user's password;
     * @return a user to update;
     * @throws Exception
     */
    public User updateUser(int id, String name, String surname, String date, String passport, String address, String email,
                           Integer balance, String password) throws Exception {
        User user = userService.getEntityById(id);
        user.setName(name); user.setSurname(surname); user.setBirthday(dateFormat.parse(date));
        user.setPassword(Converter.getMD5(password));
        if (passport != null && passport.length() !=0) {
            if (passport.length() > 100) {
                throw new IOException("The passport length is too big!");
            } else {
                user.setPassport(passport);
            }
        }
        if (address != null && address.length() !=0) {
            if (address.length() > 100) {
                throw new IOException("The address length is too big!");
            } else {
                user.setAddress(address);
            }
        }
        if (email != null && email.length() !=0) {
            if (email.length() > 50) {
                throw new IOException("The email length is too big!");
            } else {
                user.setEmail(email);
            }
        }
        if (balance != null) {
            if (balance > 100000) {
                throw new IOException("The amount is too high!");
            } else {
                user.setBalance(balance);
            }
        }
        return user;
    }
}
