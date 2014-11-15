package ru.tsystems.tproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tsystems.tproject.entities.Contract;
import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.entities.Tariff;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.integration.ContractValidator;
import ru.tsystems.tproject.utils.Parser;
import ru.tsystems.tproject.services.API.ContractService;
import ru.tsystems.tproject.services.API.OptionService;
import ru.tsystems.tproject.services.API.TariffService;
import ru.tsystems.tproject.services.API.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A controller to dispatch the queries of the employees.
 */
@Controller
public class EmployeeController {
    @Autowired
    private UserService userService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private TariffService tariffService;
    @Autowired
    private OptionService optionService;
    @Autowired
    private ContractValidator contractValidator;


    /*@RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getAll() {
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }*/
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Locale locale, Model model) {
        return "login";
    }

    @RequestMapping(value = "/cp_employee_main", method = RequestMethod.GET)
    public String mainPage(Locale locale, Model model) {
        return "cp_employee/cp_employee_main";
    }

    /**
     * This method resolves a page with all contracts. When contractId is specified, the contract is deleted.
     * @param id the id of the contract;
     * @param locale locale;
     * @param model model;
     * @return cp_employee_contracts.jsp
     */
    @RequestMapping(value = "/cp_employee_contracts", method = RequestMethod.GET)
    public String contractsPage(@RequestParam(value = "contractId", required = false) Integer id,
                                Locale locale, Model model) {
        if (id != null && id != 0) {
            Contract contract = contractService.getEntityById(id);
            User user = userService.getUserByNumber(contract.getNumber());
            contractService.deleteEntity(contract);
        }
        model.addAttribute("contractsList", contractService.getAll());
        return "cp_employee/cp_employee_contracts";
    }
    @RequestMapping(value = "/cp_employee_new_contract", method = RequestMethod.GET)
    public String addContract(@RequestParam(value = "id", required = false) Integer id, Locale locale, Model model) {
        if (id != null) {
            model.addAttribute("currentLogin", userService.getEntityById(id).getLogin());
        }
        model.addAttribute("tariffsList", tariffService.getAll());
        return "cp_employee/cp_employee_new_contract";
    }
    @RequestMapping(value = "/cp_employee_new_contract_options", method = RequestMethod.POST)
    public String addContractOptions(@RequestParam(value = "number") String contractNumber,
                                     @RequestParam(value = "login") String login,
                                     @RequestParam(value = "cb") int tariffId,
                                     Locale locale, Model model) {
        //добавить проверку, существует ли пользователь!
        int userID = userService.getUserByLogin(login).getId();
        Tariff tariff = tariffService.getEntityById(tariffId);
        model.addAttribute("optionsList", tariff.getPossibleOptions());
        model.addAttribute("contractNumber", Parser.doParse(contractNumber));
        model.addAttribute("userId", userID);
        model.addAttribute("tariffId", tariffId);
        model.addAttribute("tariff", tariff);
        return "cp_employee/cp_employee_new_contract_options";
    }

    /** This method creates a contract, if the entered data is valid, and redirects back to the page with options otherwise.
     * The array with options' id is validated by a contractValidator. If something is incorrect, the exception is
     * added to the exceptionsList.
     *
     * @param contractNumber a number of the contract;
     * @param userID the id of the user;
     * @param tariffId the tariff id;
     * @param array the array of selected options' ids;
     * @param locale locale
     * @param model model
     * @return cp_employee_contract_created.jsp
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/cp_employee_contract_created", method = RequestMethod.POST)
    public String createContract(@RequestParam(value = "number") String contractNumber,
                                 @RequestParam(value = "userID") int userID,
                                 @RequestParam(value = "tariffID") int tariffId,
                                 @RequestParam(value = "cb", required = false) int[] array,
                                 Locale locale, Model model) {
        User user = userService.getEntityById(userID);
        long number = Long.parseLong(Parser.doParse(contractNumber));
        Tariff tariff = tariffService.getEntityById(tariffId);
        Contract contract = new Contract(number, user, tariff);
        if (array == null || array.length == 0)  {
            contractService.createEntity(contract);
            //сделать по феншую?
            user.addContract(contract);
            userService.updateEntity(user);
            return "cp_employee/cp_employee_contract_created";
        }
        else {
            List<Exception> exceptionList = new ArrayList<>();
            List validationResultList = contractValidator.validateOptions(array, exceptionList); //checking if the entered options are correct
            List<Option> optionList = (List<Option>) validationResultList.get(0);
            exceptionList = (List<Exception>) validationResultList.get(1);
            if (exceptionList.isEmpty()) {
                for (Option x : optionList) {
                    contract.addOption(x);
                }
                contractService.createEntity(contract);
                user.addContract(contract);
                userService.updateEntity(user);
                return "cp_employee/cp_employee_contract_created";
            }
            else {
                model.addAttribute("optionsList", tariff.getPossibleOptions());
                model.addAttribute("contractNumber", number);
                model.addAttribute("userId", userID);
                model.addAttribute("tariffId", tariffId);
                model.addAttribute("tariff", tariff);
                model.addAttribute("areExceptions", "true");
                model.addAttribute("exceptionsList", exceptionList);
                return "cp_employee/cp_employee_new_contract_options";
            }
        }

    }

    /**
     * This method returns a page where you change the contract.
     * If the number of contract is specified in the request, the contract gets blocked / unblocked.
     * @param contractId the ID of the contract;
     * @param contractNumber the number of the contract;
     * @param locale locale;
     * @param model model;
     * @return cp_employee_change_contract.jsp
     */
    @RequestMapping(value = "/cp_employee_change_contract", method = RequestMethod.GET)
    public String changeContract(@RequestParam(value = "contractId") Integer contractId,
                                 @RequestParam(value = "contractNumber", required = false) Long contractNumber,
                                 Locale locale, Model model) {
        Contract contract = contractService.getEntityById(contractId);
        if (contractNumber != null) {
            contract.setBlocked(!contract.isBlocked());
            contractService.updateEntity(contract);
        }
        if (contract.isBlocked()) {
            model.addAttribute("paramIsBlocked", "ВКЛЮЧЕНА");
            model.addAttribute("action", "Разблокировать");
        }
        else {
            model.addAttribute("paramIsBlocked", "выключена");
            model.addAttribute("action", "Заблокировать");
        }
        model.addAttribute("tariffsList", tariffService.getAll());
        model.addAttribute("number", contract.getNumber());
        model.addAttribute("login", contract.getUser().getLogin());
        model.addAttribute("contractId", contractId);
        return "cp_employee/cp_employee_change_contract";
    }
}
