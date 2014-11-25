package ru.tsystems.tproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.integration.ContractValidator;
import ru.tsystems.tproject.services.API.OptionService;
import ru.tsystems.tproject.utils.Locale.EnglishLanguage;
import ru.tsystems.tproject.utils.Locale.RussianLanguage;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * A controller to dispatch the ajax queries.
 */
@Controller
public class AjaxDispatcher {
    @Autowired
    private OptionService optionService;
    @Autowired
    private ContractValidator contractValidator;
    @RequestMapping(value = "cp_get_options_for_tariff/{tariffId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Option> getOptionsForTariff(@PathVariable int tariffId) {
        List<Option> optionList = new ArrayList<>();
        for (Option option: optionService.getAllOptionsForTariff(tariffId)) {
            optionList.add(new Option(option.getId(), option.getName(), option.getPrice(), option.getInitialPrice()));
        }
        return optionList;
    }
    @RequestMapping(value = "cp_get_optionsTogether_for_option/{optionId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Option> getOptionsTogether(@PathVariable int optionId) {
        List<Option> optionList = new ArrayList<>();
        for (Option option: optionService.getAllOptionsTogetherForOption(optionId)) {
            optionList.add(new Option(option.getId(), option.getName(), option.getPrice(), option.getInitialPrice()));
        }
        return optionList;
    }
    @RequestMapping(value = "cp_get_optionsIncompatible_for_option/{optionId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Option> getOptionsIncompatible(@PathVariable int optionId) {
        List<Option> optionList = new ArrayList<>();
        for (Option option: optionService.getAllOptionsIncompatibleForOption(optionId)) {
            optionList.add(new Option(option.getId(), option.getName(), option.getPrice(), option.getInitialPrice()));
        }
        return optionList;
    }
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "cp_ajax_validate_options", method = RequestMethod.POST)
    @ResponseBody
    public List<String> validateOptions(@RequestParam(value = "cb3", required = false) int[] array,
                                        @RequestParam(value = "currentUserID", required = false) Integer userID) {
        if (array == null || array.length == 0) return null;
        else {
            List returnList = new ArrayList();
            List<Exception> list = new ArrayList<>();
            if (userID != null && userID !=0) {
                returnList = contractValidator.validateOptions(array, list, userID);
            }
            else {
                returnList = contractValidator.validateOptions(array, list, 0);
            }
            list = (List<Exception>) returnList.get(1);
            if (list.isEmpty()) return null;
            else {
                List<String> messageList = new ArrayList<>();
                for (Exception ex: list) {
                    messageList.add(ex.getMessage());
                }
                return messageList;
            }
        }
    }
    @RequestMapping(value = "cp_language_en", method = RequestMethod.GET)
    @ResponseBody
    public String setEnglish(HttpServletRequest request) {
        request.getSession().removeAttribute("language");
        request.getSession().setAttribute("language", EnglishLanguage.getEnglishLanguage());
        return "";
    }
    @RequestMapping(value = "cp_language_ru", method = RequestMethod.GET)
    @ResponseBody
    public String setRussian(HttpServletRequest request) {
        request.getSession().removeAttribute("language");
        request.getSession().setAttribute("language", RussianLanguage.getRussianLanguage());
        return "";
    }
}
