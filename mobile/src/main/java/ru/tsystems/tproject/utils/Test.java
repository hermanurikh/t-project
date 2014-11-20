package ru.tsystems.tproject.utils;

import ru.tsystems.tproject.utils.Locale.EnglishLanguage;
import ru.tsystems.tproject.utils.Locale.RussianLanguage;
import ru.tsystems.tproject.utils.Locale.Translatable;

import java.text.SimpleDateFormat;

/**
 * Created by german on 15.11.14.
 */

public class Test {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    public static void main(String[] args) throws Exception{
        Translatable object = RussianLanguage.getRussianLanguage();
        System.out.println(object.getJSP_BALANCE_CURRENCY());

    }
}

