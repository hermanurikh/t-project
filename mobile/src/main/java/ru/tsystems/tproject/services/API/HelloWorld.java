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
        OptionService optionService = new OptionServiceImplementation();
        ContractService contractService = new ContractServiceImplementation();
        String[] array = new String[3];
        Option option;
        List<Option> optionsTogether = new ArrayList<>();
        List<Option> optionsIncompatible = new ArrayList<>();
        List<Option> temporaryList = new ArrayList<>();
        List<Exception> exceptionsList = new ArrayList<>();


            array[0] = "6";
             array[1] = "7";
             array[2] = "10";//checkbox of options

            Long number = Long.parseLong("1234568796");
            int userId = 2;
            int tariffId = 7;
            Contract contract = new Contract(number, userService.getUserById(userId), tariffService.getTariffById(tariffId));
            int optionId = 0;
            for (String x : array) {
                optionId = Integer.parseInt(x);
                option = optionService.getOptionById(optionId);
                temporaryList.add(option);
            }
            if (temporaryList.isEmpty()) { // we do not need to check anything if there are no options
                System.out.println("templist пуст, созидаем");
            }
            else {
                for (Option x : temporaryList) { // for each option
                    optionsTogether = x.getOptionsTogether(); // we get a list of necessary options
                    if (!optionsTogether.isEmpty()) {
                        for (Option necessary : optionsTogether) { //for each option from the together list we check whether it was checked
                            if (!temporaryList.contains(necessary)) { //if it wasn't
                                exceptionsList.add(new Exception("You didn't select the " + necessary.getName() + " option, but it was necessary for the option " + x.getName()));
                            }
                        }
                    }
                    optionsIncompatible = x.getOptionsIncompatible(); //we get a list of incompatible options
                    if (!optionsIncompatible.isEmpty()) {
                        for (Option incompatible : optionsIncompatible) {
                            if (temporaryList.contains(incompatible)) {
                                exceptionsList.add(new Exception("You selected the " + incompatible.getName() + " option, but it can't be selected with the option " + x.getName()));
                            }
                        }
                    }
                }
                if (exceptionsList.isEmpty()) {
                    for (Option x : temporaryList) {
                        contract.addOption(x);
                    }
                    contractService.createContract(contract);
                    System.out.println("успех, противоречий нет");
                }
                else {
                    for (Exception x : exceptionsList) System.out.println(x.getMessage());
                }
            }
        }



}

