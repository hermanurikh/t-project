package ru.tsystems.tproject.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.services.API.OptionService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by german on 15.11.14.
 */
@Service
public class Test {

    @Autowired
    private OptionService optionService;
    public static void main(String[] args) {
Test test = new Test();
        test.test();
    }
    private void test(){
        int[] array = new int[2];
        array[0] = 6; array[1] = 15;
        List<Option> optionsTogether;
        List<Option> optionsIncompatible;
        List<Option> temporaryList = new ArrayList<>();
        for (int x : array) {
            Option option = optionService.getEntityById(x);
            temporaryList.add(option);
        }
        for (Option x : temporaryList) { // for each option
            optionsTogether = x.getOptionsTogether(); // we get a list of necessary options
            if (!optionsTogether.isEmpty()) {
                for (Option necessary : optionsTogether) { //for each option from the together list we check whether it was checked

                    if (!temporaryList.contains(necessary)) {
                        System.out.println("list doesn't have the options " + necessary);
                    }
                }
            }
            optionsIncompatible = x.getOptionsIncompatible(); //we get a list of incompatible options
            if (!optionsIncompatible.isEmpty()) {
                for (Option incompatible : optionsIncompatible) {
                    if (temporaryList.contains(incompatible)) {
                        System.out.println("list has the option incompatible " + incompatible);

                    }
                }
            }
        }
    }
}
