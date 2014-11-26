package ru.tsystems.tproject.utils;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.utils.locale.EnglishLanguage;
import ru.tsystems.tproject.utils.locale.Translatable;

import java.text.SimpleDateFormat;

/**
 * Created by german on 15.11.14.
 */

public class Test {
    private static Logger logger = Logger.getLogger(Test.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    public static void main(String[] args) {
        Translatable translatable = EnglishLanguage.getEnglishLanguage();
        System.out.println(translatable.getJSP_CONTRACTS_BLOCKED_BY_ADMIN());
    }

    }

