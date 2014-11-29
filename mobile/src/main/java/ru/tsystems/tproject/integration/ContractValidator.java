package ru.tsystems.tproject.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.services.API.OptionService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ru.tsystems.tproject.services.API.UserService;

/**
 * A class that handles the validation of the contracts creation.
 */
@Service("contractValidator")
public class ContractValidator {
    @Autowired
    private OptionService optionService;
    @Autowired
    private UserService userService;
    private static final int MAX_PRICE = 400000;

    /**
     * This method checks if the selected options are compatible with each other.
     * @param array the array of options' ids;
     * @param exceptionsList the list of exceptions occured;
     * @param userId the id of the user to check the balance;
     * @return a list with a list of options and a list of exceptions.
     */
    public List validateOptions(int[] array, List<Exception> exceptionsList, int userId) {
        List<Option> optionsTogether;
        List<Option> optionsIncompatible;
        List<Integer> temporaryList = new ArrayList<>();
        List<Option> optionList = new ArrayList<>();
        for (int x : array) {
            temporaryList.add(x);
        }
        // for each option
        for (Integer x : temporaryList) {
            Option currentOption = optionService.getEntityById(x);
            //we add it to the final list
            optionList.add(currentOption);
            // we get a list of necessary options
            optionsTogether = currentOption.getOptionsTogether();
            //for each option from the together list we check whether it was
            if (!optionsTogether.isEmpty()) {
                for (Option necessary : optionsTogether) {
                    if (!temporaryList.contains(necessary.getId())) { //if it wasn't
                        exceptionsList.add(new Exception("You didn't select the " + necessary.getName() + " option, but it was necessary for the option " + currentOption.getName()));
                    }
                }
            }
            //we get a list of incompatible options
            optionsIncompatible = currentOption.getOptionsIncompatible();
            if (!optionsIncompatible.isEmpty()) {
                for (Option incompatible : optionsIncompatible) {
                    if (temporaryList.contains(incompatible.getId())) {
                        exceptionsList.add(new Exception("You selected the " + incompatible.getName() + " option, but it can't be selected with the option " + currentOption.getName()));
                    }
                }
            }
        }
        if (exceptionsList.isEmpty() && userId != 0) {
            //a check for balance - is the required sum on the account
            int balance = balanceCheck(userId, optionList);
            if (balance < 0) {
                exceptionsList.add(new Exception("You do not have enough money on your account to perform the necessary action. Please refill your balance in the amount of " + (0 - balance) + " roubles."));
            }
        }
        List list = new ArrayList<>();
        Collections.addAll(list, optionList, exceptionsList);
        return list;
    }

    /**
     * This method asserts whether selected options can be acquired to the user's account.
     * @param userId the user's id;
     * @param optionList the list of options;
     * @return the remaining sum
     */
    public int balanceCheck(int userId, List<Option> optionList) {
        int balance = userService.getEntityById(userId).getBalance();
        for (Option x : optionList) {
            balance-=x.getInitialPrice();
        }
        return balance;
    }

    /**
     * This method validates the entered sum. It should be between 0 and 400000.
     * @param price the entered sum;
     * @param priceName the name of the param to throw the explainable exception;
     * @throws IOException
     */
    public void priceCheck(int price, String priceName) throws IOException {
        if (price > MAX_PRICE) {
            throw new IOException(String.format("The %s is too high!", priceName));
        }
        if (price < 0) {
            throw new IOException(String.format("The %s must be > 0!", priceName));
        }
    }
}
