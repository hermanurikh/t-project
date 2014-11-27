package ru.tsystems.tproject.utils.pages;

/**
 * A class to hold all the constants with shared addresses.
 */
public class SharedPages {
    public static final String LOGIN = "login";
    public static final String LOGOUT = "logout";
    public static final String DENIED = "denied";
    public static final String LOGIN_DENIED = "login-denied";
    public static final String MAIN = "main";
    public static final String CLIENT_MAIN = ClientPages.CLIENT + ClientPages.MAIN;
    public static final String EMPLOYEE_MAIN = EmployeePages.EMPLOYEE + EmployeePages.MAIN;
    //ajax queries
    public static final String GET_OPTIONS_FOR_TARIFF = "cp_get_options_for_tariff/{tariffId}";
    public static final String GET_OPTIONS_TOGETHER = "cp_get_optionsTogether_for_option/{optionId}";
    public static final String GET_OPTIONS_INCOMPATIBLE = "cp_get_optionsIncompatible_for_option/{optionId}";
    public static final String VALIDATE_OPTIONS = "cp_ajax_validate_options";
    public static final String EN = "cp_language_en";
    public static final String RU = "cp_language_ru";
}

