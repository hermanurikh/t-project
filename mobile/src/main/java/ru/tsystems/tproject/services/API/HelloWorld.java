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
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");
    private static Logger logger = Logger.getLogger(HelloWorld.class);
    public static void main(String[] args) throws Exception{
        TariffService tariffService = new TariffServiceImplementation();
        OptionService optionService = new OptionServiceImplementation();
        ContractService contractService = new ContractServiceImplementation();
        UserService userService = new UserServiceImplementation();


        String[] array = new String[2]; //checkbox of options
        array[0] = "6";
        array[1] = "7";
        long number = Long.parseLong("9817710004");
        int userId = Integer.parseInt("1");
        int tariffId = Integer.parseInt("8");
        Contract contract = new Contract(number, userService.getUserById(userId), tariffService.getTariffById(tariffId));
        int optionId = 0;
        for (String x : array) {
            optionId = Integer.parseInt(x);
            contract.addOption(optionService.getOptionById(optionId));
        }
        contractService.createContract(contract);
        System.out.println("success");
    }
}
