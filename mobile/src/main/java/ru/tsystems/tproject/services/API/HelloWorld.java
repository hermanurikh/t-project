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
    public static void main(String[] args) throws Exception{
        UserService userService = new UserServiceImplementation();
        RoleService roleService = new RoleServiceImplementation();
        Date success = dateFormat.parse("2014-10-19");

        String name = "Александр";
        String surname =  "Санников";
        Date birthday = success;
        String passport = "паспорт";
        String address = "адрес";
        String email = "email@lol.ru";
        String login = "alex";
        int balance = 1000;
        String password = "qwertydjan";
        int role = 1;
        userService.createUser(new User(name, surname, birthday, passport, address, email, login, balance, password, roleService.getRoleById(role)));
        System.out.println("user created");
    }
}
