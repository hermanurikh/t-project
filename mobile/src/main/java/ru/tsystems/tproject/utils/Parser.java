package ru.tsystems.tproject.utils;

/**
 * A parser class to parse different numbers like contractNumber.
 * It parses "(555) 555-55-55" to "5555555555".
 */
public class Parser {
    /**
     * The parser of the number
     * @param number initial text;
     * @return parsed number.
     */
    public static String doParse(String number) {
        return number.replaceAll(" ", "").replaceAll("-", "").replaceAll("\\(", "").replaceAll("\\)", "");
    }

}
