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
        System.out.println("<tr name=\"trow\" class=\"ui-table-data-row ui-state-even ui-selected\" onclick=\"changeDiv('${option.id}150')\">\n" +
                "\n" +
                "                                    <td name=\"tcell\" class=\"simplecell_checkbox\" align=\"left\"><input type=\"checkbox\" name=\"cb\" id=${option.id} value=${option.id}></td>\n" +
                "                                    <td class=\"simplecell\" name=\"tcell\" style=\"vertical-align: top; width: 150px\"><span>${option.name}</span><br></td>\n" +
                "                                    <td class=\"simplecell\" name=\"tcell\" style=\"vertical-align: top; width: 100px;\"><span>${option.price}</span></td>\n" +
                "                                    <td class=\"simplecell\" name=\"tcell\" style=\"vertical-align: top; width: 100px;\"><span>${option.initialPrice}</span></td>\n" +
                "                                </tr>");

    }
}

