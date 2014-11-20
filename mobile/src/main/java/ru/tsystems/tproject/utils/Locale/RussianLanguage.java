package ru.tsystems.tproject.utils.Locale;

/**
 * A singleton class to hold the static String values for the Russian language.
 */
public class RussianLanguage implements Translatable {
    private static RussianLanguage russianLanguage;
    private final String JSP_CONTRACTS_NAME = "Контракты";
    private final String JSP_BALANCE_NAME = "Баланс";
    private final String JSP_INFO_NAME = "Информация об аккаунте";
    private final String JSP_PROFILE_NAME = "Профиль";
    private final String JSP_SUPPORT_NAME = "Служба поддержки";
    private final String JSP_INVITE_NAME = "Добро пожаловать";
    private final String JSP_LOGOUT_NAME = "Выход из аккаунта";
    private final String JSP_CONTRACTS_LIST_NAME = "Список контрактов";
    private final String JSP_CONTRACTS_LIST_NUMBER = "Номер";
    private final String JSP_CONTRACTS_LIST_TARIFF = "Тариф";
    private final String JSP_CONTRACTS_LIST_OPTIONS = "Опции";
    private final String JSP_CONTRACTS_LIST_ACTIONS = "Действия";
    private final String JSP_CONTRACTS_ACTION_DETAILED = "Подробней";
    private final String JSP_CONTRACTS_ACTION_CHANGE = "Изменить";
    private final String JSP_CONTRACTS_DETAILED_VIEW_CONTRACT = "Просмотр контракта";
    private final String JSP_CONTRACTS_DETAILED_VIEW_TARIFF = "Подключенный тариф";
    private final String JSP_CONTRACTS_DETAILED_VIEW_PRICE = "Стоимость, в месяц";
    private final String JSP_CONTRACTS_DETAILED_VIEW_OPTIONS = "Подключенные опции";
    private final String JSP_CONTRACTS_DETAILED_VIEW_OVERALL_PRICE = "Суммарная стоимость тарифа с опциями";
    private final String JSP_CONTRACTS_DETAILED_VIEW_IS_BLOCKED = "Состояние контракта";
    private final String JSP_CONTRACTS_CHANGE = "Изменение контракта с номером ";
    private final String JSP_CONTRACTS_CHOOSE_TARIFF = "Выберите тариф для контракта";
    private final String JSP_CONTRACTS_PRICE = "Цена";
    private final String JSP_CONTRACTS_SUBMIT = "Выбрать опции для тарифа";
    private final String JSP_CONTRACTS_BLOCK = "Заблокировать";
    private final String JSP_CONTRACTS_BLOCKED = "В данный момент блокировка ВКЛЮЧЕНА. Вы не можете произвести изменения с контрактом.";
    private final String JSP_CONTRACTS_UNBLOCK = "Разблокировать";
    private final String JSP_CONTRACTS_UNBLOCKED = "В данный момент блокировка выключена.";
    private final String JSP_CONTRACTS_CHANGING = "Изменение контракта";
    private final String JSP_CONTRACTS_CHANGING_NUMBER = "Номер контракта:";
    private final String JSP_CONTRACTS_CHOOSE_OPTIONS = "Выберите опции для тарифа";
    private final String JSP_CONTRACTS_DETAILED_OPTIONS = "Щелкните по опции для просмотра ее необходимых и несовместимых опций.";
    private final String JSP_CONTRACTS_OPTION = "Опция";
    private final String JSP_CONTRACTS_OPTION_PRICE = "Цена";
    private final String JSP_CONTRACTS_OPTION_INITIAL_PRICE = "Цена подключения";
    private final String JSP_CONTRACTS_OPTIONS_TOGETHER = "Необходимые опции";
    private final String JSP_CONTRACTS_OPTIONS_INCOMPATIBLE = "Несовместимые опции";
    private final String JSP_CONTRACTS_SUBMIT_CONTRACT = "Изменить контракт";
    private final String JSP_CONTRACTS_SUBMIT_BUCKET = "Пожалуйста, подтвердите изменение контракта";
    private final String JSP_CONTRACTS_SUBMIT_BUCKET_TARIFF_OPTIONS = "Выбранный тариф и опции:";
    private final String JSP_SUCCESS = "Успешно!";
    private final String JSP_BALANCE_PAY = "Оплатить услуги";
    private final String JSP_BALANCE_INCREASE_BALANCE = "Пополнить баланс на 100 рублей";
    private final String JSP_BALANCE_CURRENT = "Текущий баланс";
    private final String JSP_BALANCE_CURRENCY = "р.";
    private final String JSP_INFO_PROFILE = "Профиль аккаунта";
    private final String JSP_INFO_BIRTHDAY = "Дата рождения";
    private final String JSP_INFO_PASSPORT = "Паспорт";
    private final String JSP_INFO_CONTACT_INFO = "Контактная информация";
    private final String JSP_INFO_EMAIL = "Эл. почта";
    private final String JSP_INFO_POST = "Почтовый адрес";

    private RussianLanguage() {
    }
    public static RussianLanguage getRussianLanguage() {
        if (russianLanguage == null) {
            return russianLanguage = new RussianLanguage();
        }
        else {
            return russianLanguage;
        }
    }
    @Override
    public String getJSP_CONTRACTS_NAME() {
        return JSP_CONTRACTS_NAME;
    }
    @Override
    public String getJSP_BALANCE_NAME() {
        return JSP_BALANCE_NAME;
    }
    @Override
    public String getJSP_INFO_NAME() {
        return JSP_INFO_NAME;
    }
    @Override
    public String getJSP_PROFILE_NAME() {
        return JSP_PROFILE_NAME;
    }
    @Override
    public String getJSP_SUPPORT_NAME() {
        return JSP_SUPPORT_NAME;
    }
    @Override
    public String getJSP_INVITE_NAME() {
        return JSP_INVITE_NAME;
    }
    @Override
    public String getJSP_LOGOUT_NAME() {
        return JSP_LOGOUT_NAME;
    }
    @Override
    public String getJSP_CONTRACTS_LIST_NAME() {
        return JSP_CONTRACTS_LIST_NAME;
    }
    @Override
    public String getJSP_CONTRACTS_LIST_NUMBER() {
        return JSP_CONTRACTS_LIST_NUMBER;
    }
    @Override
    public String getJSP_CONTRACTS_LIST_TARIFF() {
        return JSP_CONTRACTS_LIST_TARIFF;
    }
    @Override
    public String getJSP_CONTRACTS_LIST_OPTIONS() {
        return JSP_CONTRACTS_LIST_OPTIONS;
    }
    @Override
    public String getJSP_CONTRACTS_LIST_ACTIONS() {
        return JSP_CONTRACTS_LIST_ACTIONS;
    }
    @Override
    public String getJSP_CONTRACTS_ACTION_DETAILED() {
        return JSP_CONTRACTS_ACTION_DETAILED;
    }
    @Override
    public String getJSP_CONTRACTS_ACTION_CHANGE() {
        return JSP_CONTRACTS_ACTION_CHANGE;
    }
    @Override
    public String getJSP_CONTRACTS_DETAILED_VIEW_CONTRACT() {
        return JSP_CONTRACTS_DETAILED_VIEW_CONTRACT;
    }
    @Override
    public String getJSP_CONTRACTS_DETAILED_VIEW_TARIFF() {
        return JSP_CONTRACTS_DETAILED_VIEW_TARIFF;
    }
    @Override
    public String getJSP_CONTRACTS_DETAILED_VIEW_PRICE() {
        return JSP_CONTRACTS_DETAILED_VIEW_PRICE;
    }
    @Override
    public String getJSP_CONTRACTS_DETAILED_VIEW_OPTIONS() {
        return JSP_CONTRACTS_DETAILED_VIEW_OPTIONS;
    }
    @Override
    public String getJSP_CONTRACTS_DETAILED_VIEW_OVERALL_PRICE() {
        return JSP_CONTRACTS_DETAILED_VIEW_OVERALL_PRICE;
    }
    @Override
    public String getJSP_CONTRACTS_DETAILED_VIEW_IS_BLOCKED() {
        return JSP_CONTRACTS_DETAILED_VIEW_IS_BLOCKED;
    }
    @Override
    public String getJSP_CONTRACTS_CHANGE() {
        return JSP_CONTRACTS_CHANGE;
    }
    @Override
    public String getJSP_CONTRACTS_CHOOSE_TARIFF() {
        return JSP_CONTRACTS_CHOOSE_TARIFF;
    }
    @Override
    public String getJSP_CONTRACTS_PRICE() {
        return JSP_CONTRACTS_PRICE;
    }
    @Override
    public String getJSP_CONTRACTS_SUBMIT() {
        return JSP_CONTRACTS_SUBMIT;
    }
    @Override
    public String getJSP_CONTRACTS_BLOCK() {
        return JSP_CONTRACTS_BLOCK;
    }
    @Override
    public String getJSP_CONTRACTS_BLOCKED() {
        return JSP_CONTRACTS_BLOCKED;
    }
    @Override
    public String getJSP_CONTRACTS_UNBLOCK() {
        return JSP_CONTRACTS_UNBLOCK;
    }
    @Override
    public String getJSP_CONTRACTS_UNBLOCKED() {
        return JSP_CONTRACTS_UNBLOCKED;
    }
    @Override
    public String getJSP_CONTRACTS_CHANGING() {
        return JSP_CONTRACTS_CHANGING;
    }
    @Override
    public String getJSP_CONTRACTS_CHANGING_NUMBER() {
        return JSP_CONTRACTS_CHANGING_NUMBER;
    }
    @Override
    public String getJSP_CONTRACTS_CHOOSE_OPTIONS() {
        return JSP_CONTRACTS_CHOOSE_OPTIONS;
    }
    @Override
    public String getJSP_CONTRACTS_DETAILED_OPTIONS() {
        return JSP_CONTRACTS_DETAILED_OPTIONS;
    }
    @Override
    public String getJSP_CONTRACTS_OPTION() {
        return JSP_CONTRACTS_OPTION;
    }
    @Override
    public String getJSP_CONTRACTS_OPTION_PRICE() {
        return JSP_CONTRACTS_OPTION_PRICE;
    }
    @Override
    public String getJSP_CONTRACTS_OPTION_INITIAL_PRICE() {
        return JSP_CONTRACTS_OPTION_INITIAL_PRICE;
    }
    @Override
    public String getJSP_CONTRACTS_OPTIONS_TOGETHER() {
        return JSP_CONTRACTS_OPTIONS_TOGETHER;
    }
    @Override
    public String getJSP_CONTRACTS_OPTIONS_INCOMPATIBLE() {
        return JSP_CONTRACTS_OPTIONS_INCOMPATIBLE;
    }
    @Override
    public String getJSP_CONTRACTS_SUBMIT_CONTRACT() {
        return JSP_CONTRACTS_SUBMIT_CONTRACT;
    }
    @Override
    public String getJSP_CONTRACTS_SUBMIT_BUCKET() {
        return JSP_CONTRACTS_SUBMIT_BUCKET;
    }
    @Override
    public String getJSP_CONTRACTS_SUBMIT_BUCKET_TARIFF_OPTIONS() {
        return JSP_CONTRACTS_SUBMIT_BUCKET_TARIFF_OPTIONS;
    }
    @Override
    public String getJSP_SUCCESS() {
        return JSP_SUCCESS;
    }
    @Override
    public String getJSP_BALANCE_PAY() {
        return JSP_BALANCE_PAY;
    }
    @Override
    public String getJSP_BALANCE_INCREASE_BALANCE() {
        return JSP_BALANCE_INCREASE_BALANCE;
    }
    @Override
    public String getJSP_BALANCE_CURRENT() {
        return JSP_BALANCE_CURRENT;
    }
    @Override
    public String getJSP_BALANCE_CURRENCY() {
        return JSP_BALANCE_CURRENCY;
    }
    @Override
    public String getJSP_INFO_PROFILE() {
        return JSP_INFO_PROFILE;
    }
    @Override
    public String getJSP_INFO_BIRTHDAY() {
        return JSP_INFO_BIRTHDAY;
    }
    @Override
    public String getJSP_INFO_PASSPORT() {
        return JSP_INFO_PASSPORT;
    }
    @Override
    public String getJSP_INFO_CONTACT_INFO() {
        return JSP_INFO_CONTACT_INFO;
    }
    @Override
    public String getJSP_INFO_EMAIL() {
        return JSP_INFO_EMAIL;
    }
    @Override
    public String getJSP_INFO_POST() {
        return JSP_INFO_POST;
    }
}
