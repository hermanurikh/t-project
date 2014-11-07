package ru.tsystems.tproject.DAO.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.tsystems.tproject.DAO.API.RoleDAO;
import ru.tsystems.tproject.DAO.API.UserDAO;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.services.API.UserService;
import ru.tsystems.tproject.services.implementation.UserServiceImplementation;

/**
 * Created by german on 07.11.14.
 */
public class test {
    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        test test = new test();
        try {
        test.test(); }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    public void test() {
        userService.getUserByLogin("hermanurikh");
    }
}
