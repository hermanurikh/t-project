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
        TariffService tariffService = new TariffServiceImplementation();

        String contractNumber = "5468796321";
        String login = "alex";
        int userID = userService.getUserByLogin(login).getId();
        int tariffID = Integer.parseInt("7");
        Tariff tariff = tariffService.getTariffById(tariffID);
        List<Option> optionsList = tariff.getPossibleOptions();
        for (Option x : optionsList) {
            if (!x.getOptionsTogether().isEmpty()) {
                List<Integer> togetherList = null;
                for (Option y : x.getOptionsTogether()) {
                    togetherList.add(y.getId());
                }
                int[] idTogetherList = new int[togetherList.size()];
                for (int i = 0; i < togetherList.size(); i++)
                    idTogetherList[i] = togetherList.get(i); // an array of compatible options' ids
            }
            if (!x.getOptionsIncompatible().isEmpty()) {
                List<Integer> incompatibleList = null;
                for (Option y : x.getOptionsIncompatible()) {
                    incompatibleList.add(y.getId());
                }
                int[] idIncompatibleList = new int[incompatibleList.size()];
                for (int i = 0; i < incompatibleList.size(); i++)
                    idIncompatibleList[i] = incompatibleList.get(i); // an array of incompatible options' ids
            }
        }


    }
}

