package ru.tsystems.tproject.services.API;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.entities.Contract;
import ru.tsystems.tproject.entities.Role;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.exceptions.CustomDAOException;
import ru.tsystems.tproject.services.implementation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by german on 19.10.14.
 */
public class HelloWorld {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static Logger logger = Logger.getLogger(HelloWorld.class);

    public static void main(String[] args) throws Exception {
        UserService userService = new UserServiceImplementation();
        RoleService roleService = new RoleServiceImplementation();
        String number = "9817710004";
        String login = "hermanurikh";
        User user;

        if (number == null || number.equals("")) {
            user = userService.getUserByLogin(login);
        } else {
            long userNumber = Long.parseLong(number);
            user = userService.getUserByNumber(userNumber);
        }
        System.out.println(user);

    }
}
