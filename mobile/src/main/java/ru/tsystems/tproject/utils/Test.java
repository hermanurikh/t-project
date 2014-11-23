package ru.tsystems.tproject.utils;

import ru.tsystems.tproject.exceptions.EntityNotDeletedException;
import ru.tsystems.tproject.utils.Locale.EnglishLanguage;
import ru.tsystems.tproject.utils.Locale.RussianLanguage;
import ru.tsystems.tproject.utils.Locale.Translatable;

import java.text.SimpleDateFormat;

/**
 * Created by german on 15.11.14.
 */

public class Test {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    public static void main(String[] args) {
        try {
            Exception ex = new EntityNotDeletedException("Unable to delete user, you need to remove his contracts first");
            System.out.println(ex.getMessage());
            throw new EntityNotDeletedException("Unable to delete user, you need to remove his contracts first");
        }
        catch (EntityNotDeletedException ex) {
            System.out.println(ex.getCause());
            System.out.println(ex.getMessage());
            System.out.println(ex.getLocalizedMessage());
            System.out.println(ex.toString());
            System.out.println(ex);
        }

    }
}

