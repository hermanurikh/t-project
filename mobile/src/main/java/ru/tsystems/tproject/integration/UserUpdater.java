package ru.tsystems.tproject.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.services.API.RoleService;
import ru.tsystems.tproject.services.API.UserService;
import ru.tsystems.tproject.utils.Converter;

import java.text.SimpleDateFormat;

/**
 * Created by german on 17.11.14.
 */
@Service("userUpdater")
public class UserUpdater {
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public User createUser(String name, String surname, String date, String passport, String address, String email,
                           Integer balance, String login, String password, int roleID) throws Exception{

        User user = new User();
        user.setName(name); user.setSurname(surname); user.setBirthday(dateFormat.parse(date));
        user.setLogin(login); user.setPassword(Converter.getMD5(password)); user.setRole(roleService.getEntityById(roleID));
        if (passport != null && passport.length() !=0) {
            user.setPassport(passport);
        }
        if (address != null && address.length() !=0) {
            user.setAddress(address);
        }
        if (email != null && email.length() !=0) {
            user.setEmail(email);
        }
        if (balance != null) {
            user.setBalance(balance);
        }
        return user;
    }
    public User updateUser(int id, String name, String surname, String date, String passport, String address, String email,
                           Integer balance, String password) throws Exception {
        User user = userService.getEntityById(id);
        user.setName(name); user.setSurname(surname); user.setBirthday(dateFormat.parse(date));
        user.setPassword(Converter.getMD5(password));
        if (passport != null && passport.length() !=0) {
            user.setPassport(passport);
        }
        if (address != null && address.length() !=0) {
            user.setAddress(address);
        }
        if (email != null && email.length() !=0) {
            user.setEmail(email);
        }
        if (balance != null) {
            user.setBalance(balance);
        }
        return user;
    }
}
