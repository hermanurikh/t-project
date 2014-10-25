package ru.tsystems.tproject.services.API;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.controllers.Converter;
import ru.tsystems.tproject.entities.Contract;
import ru.tsystems.tproject.entities.Role;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.exceptions.CustomDAOException;
import ru.tsystems.tproject.services.implementation.*;

import java.security.MessageDigest;
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

            String username = "hermanurikh";
            if (username.length() > 100) throw new Exception("Login is too long");
            else {
                String password = Converter.getMD5("gu14929.cyber8");
                User user = userService.getUserByLogin(username);
                if (user == null) throw new Exception("There is no user with the login " + username);
                else {
                    if (user.getPassword().equals(password)) {
                        if (user.getRole().getId() == 1) {
                            System.out.println("client");
                        } else if (user.getRole().getId() == 2) {
                            System.out.println("admin");;
                        } else {
                            throw new Exception("The role of user is undefined");
                        }
                    } else throw new Exception("The users passwords do not match");
                }
            }
        }



    }

