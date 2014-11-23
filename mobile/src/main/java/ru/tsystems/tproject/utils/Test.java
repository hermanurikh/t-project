package ru.tsystems.tproject.utils;

import org.apache.log4j.Logger;
import ru.tsystems.tproject.exceptions.EntityNotDeletedException;
import ru.tsystems.tproject.utils.Locale.EnglishLanguage;
import ru.tsystems.tproject.utils.Locale.RussianLanguage;
import ru.tsystems.tproject.utils.Locale.Translatable;

import java.text.SimpleDateFormat;

/**
 * Created by german on 15.11.14.
 */

public class Test {
    private static Logger logger = Logger.getLogger(Test.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    public static void main(String[] args) {
        int a = 0;
        try {
            System.out.println(45 / a);
        }
        catch (Exception x) {
            for (StackTraceElement b : x.getStackTrace()) {
                System.out.println(b);
            }
        }

    }
}

