package ru.tsystems.tproject.utils.Locale;

/**
 * A class to hold the static String values for the Russian language.
 */
public class EnglishLanguage  {

    private static EnglishLanguage englishLanguage;
    private EnglishLanguage() {
    }
    public static EnglishLanguage getEnglishLanguage() {
        if (englishLanguage == null) {
            return englishLanguage = new EnglishLanguage();
        }
        else {
            return englishLanguage;
        }
    }


    private final String JSP_CONTRACTS_NAME = "Contracts";
}
