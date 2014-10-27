package ru.tsystems.tproject.services.API;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.controllers.Converter;
import ru.tsystems.tproject.entities.*;
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
        ContractService contractService = new ContractServiceImplementation();
        int userId = 6;
        User user = userService.getUserById(userId);
        List<Contract> contractList = user.getContracts();
        if (!contractList.isEmpty()) {
            for (Contract x : contractList) {
            contractService.deleteContract(x);
         }
        }

        userService.deleteUser(user);
        System.out.println("success");


    }



}

