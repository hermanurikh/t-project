package ru.tsystems.tproject.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.entities.Tariff;
import ru.tsystems.tproject.services.API.OptionService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;

import javax.print.attribute.IntegerSyntax;

/**
 * A class that handles the validation of the contracts creation.
 */
@Service("contractValidator")
public class ContractValidator {
    @Autowired
    private OptionService optionService;
    Logger logger = Logger.getLogger(ContractValidator.class);

    public List validateOptions(int[] array, List<Exception> exceptionsList) {
        List<Option> optionsTogether;
        List<Option> optionsIncompatible;
        List<Integer> temporaryList = new ArrayList<>();
        List<Option> optionList = new ArrayList<>();
        for (int x : array) {
            temporaryList.add(x);
        }
        for (Integer x : temporaryList) { // for each option
            Option currentOption = optionService.getEntityById(x);
            optionList.add(currentOption); //we add it to the final list
            optionsTogether = currentOption.getOptionsTogether(); // we get a list of necessary options
            if (!optionsTogether.isEmpty()) {
                for (Option necessary : optionsTogether) { //for each option from the together list we check whether it was checked
                    if (!temporaryList.contains(necessary.getId())) { //if it wasn't
                        exceptionsList.add(new Exception("You didn't select the " + necessary.getName() + " option, but it was necessary for the option " + currentOption.getName()));
                    }
                }
            }
            optionsIncompatible = currentOption.getOptionsIncompatible(); //we get a list of incompatible options
            if (!optionsIncompatible.isEmpty()) {
                for (Option incompatible : optionsIncompatible) {
                    if (temporaryList.contains(incompatible.getId())) {
                        exceptionsList.add(new Exception("You selected the " + incompatible.getName() + " option, but it can't be selected with the option " + currentOption.getName()));
                    }
                }
            }
        }
        List list = new ArrayList<>();
        Collections.addAll(list, optionList, exceptionsList);
        return list;
    }
}
