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
import ru.tsystems.tproject.integration.UserUpdater;
import ru.tsystems.tproject.services.API.*;
import ru.tsystems.tproject.utils.Converter;
import ru.tsystems.tproject.utils.Parser;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
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
    private RoleService roleService;
    @Autowired
    private ContractValidator contractValidator;
    @Autowired
    private UserUpdater userUpdater;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


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
     * @return success.jsp
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
            return "cp_employee/success";
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
                return "cp_employee/success";
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
                                 HttpServletRequest request, Locale locale, Model model) {
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
        request.getSession().setAttribute("contractNumber", contract.getNumber());
        request.getSession().setAttribute("login", contract.getUser().getLogin());
        model.addAttribute("tariffsList", tariffService.getAll());
        model.addAttribute("number", contract.getNumber());
        model.addAttribute("login", contract.getUser().getLogin());
        model.addAttribute("contractId", contractId);
        return "cp_employee/cp_employee_change_contract";
    }

    /**
     * This method returns a page where the options for contract' changing should be selected.
     * @param tariffId the id of the tariff
     * @param request request
     * @param locale locale
     * @param model model
     * @return cp_employee_contract_change_options.jsp
     */
    @RequestMapping(value = "/cp_employee_contract_change_options", method = RequestMethod.POST)
    public String changeContractOptions(@RequestParam(value = "cb") Integer tariffId,
                                        HttpServletRequest request, Locale locale, Model model) {
        Tariff tariff = tariffService.getEntityById(tariffId);
        model.addAttribute("optionsList", tariff.getPossibleOptions());
        request.getSession().setAttribute("tariff", tariff);
        return "cp_employee/cp_employee_contract_change_options";
    }

    /** This method changes a contract, if the entered data is valid, and redirects back to the page with options otherwise.
     * The array with options' id is validated by a contractValidator. If something is incorrect, the exception is
     * added to the exceptionsList.
     * @param array the array of selected options' ids;
     * @param request request
     * @param locale locale
     * @param model model
     * @return success.jsp or cp_employee_contract_change_options.jsp
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/cp_employee_contract_changed", method = RequestMethod.POST)
    public String finalContractChange(@RequestParam(value = "cb", required = false) int[] array,
                                        HttpServletRequest request, Locale locale, Model model) {
        long number = (long) request.getSession().getAttribute("contractNumber");
        Tariff tariff = (Tariff) request.getSession().getAttribute("tariff");
        Contract contract = contractService.getContractByNumber(number);
        contract.setTariff(tariff);
        if (array == null || array.length == 0) {
            contract.removeAllOptions();
            contractService.updateEntity(contract);
            return "cp_employee/success";
        }
        else {
            List<Exception> exceptionList = new ArrayList<>();
            List validationResultList = contractValidator.validateOptions(array, exceptionList); //checking if the entered options are correct
            List<Option> optionList = (List<Option>) validationResultList.get(0);
            exceptionList = (List<Exception>) validationResultList.get(1);
            if (exceptionList.isEmpty()) {
                contract.removeAllOptions();
                for (Option x : optionList) {
                    contract.addOption(x);
                }
                contractService.updateEntity(contract);
                return "cp_employee/success";
            }
            else {
                model.addAttribute("optionsList", tariff.getPossibleOptions());
                model.addAttribute("areExceptions", "true");
                model.addAttribute("exceptionsList", exceptionList);
                return "cp_employee/cp_employee_contract_change_options";
            }
        }

    }

    /**
     * This method returns a page with all users.
     * @param locale locale
     * @param model model
     * @return cp_employee_users.jsp
     */
    @RequestMapping(value = "/cp_employee_users", method = RequestMethod.GET)
    public String getAllUsers(Locale locale, Model model) {
        model.addAttribute("usersList", userService.getAll());
        return "cp_employee/cp_employee_users";
    }

    /**
     * This method returns a page with user's creation.
     * @param locale locale
     * @param model model
     * @return cp_employee_new_user.jsp
     */
    @RequestMapping(value = "/cp_employee_new_user", method = RequestMethod.GET)
    public String createUser(Locale locale, Model model) {
        return "cp_employee/cp_employee_new_user";
    }

    /**
     * This method adds 100 to current balance.
     * @param id user's id
     * @param locale locale
     * @param model model
     * @return cp_employee_users.jsp
     */
    @RequestMapping(value = "/cp_employee_user_add_balance", method = RequestMethod.GET)
    public String addBalance(@RequestParam(value = "id") int id,
                             Locale locale, Model model) {
        User user = userService.getEntityById(id);
        user.setBalance(user.getBalance() + 100);
        userService.updateEntity(user);
        model.addAttribute("usersList", userService.getAll());
        return "cp_employee/cp_employee_users";
    }

    /**
     * This method returns a page where users' data can be changed.
     * @param id user's id
     * @param locale locale
     * @param model model
     * @return cp_employee_user_data_change.jsp
     */
    @RequestMapping(value = "cp_employee_user_data_change", method = RequestMethod.GET)
    public String changeUser(@RequestParam(value = "id") int id,
                             Locale locale, Model model) {
        User user = userService.getEntityById(id);
        model.addAttribute("id", user.getId());
        model.addAttribute("name", user.getName());
        model.addAttribute("surname", user.getSurname());
        model.addAttribute("birthday", dateFormat.format(user.getBirthday()));
        model.addAttribute("passport", user.getPassport());
        model.addAttribute("address", user.getAddress());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("login", user.getLogin());
        model.addAttribute("balance", user.getBalance());
        model.addAttribute("role", user.getRole().getId());
        return "cp_employee/cp_employee_user_data_change";
    }

    /**
     * This method deletes a user.
     * @param id user's id
     * @param locale locale
     * @param model model
     * @return cp_employee_users.jsp
     */
    @RequestMapping(value = "cp_employee_user_delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam(value = "id") int id,
                             Locale locale, Model model) {
        //добавить проверку, если есть контракты - не удалять!
        User user = userService.getEntityById(id);
        userService.deleteEntity(user);
        model.addAttribute("usersList", userService.getAll());
        return "cp_employee/cp_employee_users";
    }

    /**
     * This method creates a new user. The userUpdater bean is responsible for validating the entered data.
     * @param name user's name
     * @param surname user's surname
     * @param birthday user's birthday
     * @param passport user's passport
     * @param address user's address
     * @param email user's email
     * @param login user's login
     * @param balance user's balance
     * @param password user's password
     * @param roleId user's roleId
     * @param locale locale
     * @param model model
     * @return success.jsp
     * @throws Exception
     */
    @RequestMapping(value = "cp_employee_create_user", method = RequestMethod.POST)
    public String createUser(@RequestParam(value = "name") String name,
                             @RequestParam(value = "surname") String surname,
                             @RequestParam(value = "birthday") String birthday,
                             @RequestParam(value = "passport", required = false) String passport,
                             @RequestParam(value = "address", required = false) String address,
                             @RequestParam(value = "email", required = false) String email,
                             @RequestParam(value = "login") String login,
                             //добавить валидацию баланса
                             @RequestParam(value = "balance", required = false) Integer balance,
                             @RequestParam(value = "password") String password,
                             @RequestParam(value = "cb") int roleId,
                             Locale locale, Model model) throws Exception{

        User user = userUpdater.createUser(name, surname, birthday, passport, address, email, balance, login, password, roleId);
        userService.createEntity(user);
        return "cp_employee/success";
    }

    /**
     * This method updates a user. The userUpdater bean is responsible for validating the entered data.
     * @param id user's id
     * @param name user's name
     * @param surname user's surname
     * @param birthday user's birthday
     * @param passport user's passport
     * @param address user's address
     * @param email user's email
     * @param balance user's balance
     * @param password user's password
     * @param locale locale
     * @param model model
     * @return success.jsp
     * @throws Exception
     */
    @RequestMapping(value = "cp_employee_change_user", method = RequestMethod.POST)
    public String updateUser(@RequestParam(value = "id") int id,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "surname") String surname,
                             @RequestParam(value = "birthday") String birthday,
                             @RequestParam(value = "passport", required = false) String passport,
                             @RequestParam(value = "address", required = false) String address,
                             @RequestParam(value = "email", required = false) String email,
                             //добавить валидацию баланса
                             @RequestParam(value = "balance", required = false) Integer balance,
                             @RequestParam(value = "password") String password,
                             Locale locale, Model model) throws Exception {
        User user = userUpdater.updateUser(id, name, surname, birthday, passport, address, email, balance, password);
        userService.updateEntity(user);
        return "cp_employee/success";
    }

    /**
     * This method redirects to a page for user searching.
     * @param locale locale
     * @param model model
     * @return cp_employee_user_search.jsp
     */
    @RequestMapping(value = "cp_employee_user_search", method = RequestMethod.GET)
    public String searchForUser(Locale locale, Model model) {
        return "cp_employee/cp_employee_user_search";
    }

    /**
     * This method searches for a user. It redirects to a page where user can be changed, if the user is found, and back otherwise.
     * @param number user's contract number
     * @param login user's login
     * @param locale locale
     * @param model model
     * @return cp_employee_user_data_change.jsp or cp_employee_user_search.jsp
     */
    @RequestMapping(value = "cp_employee_find_user", method = RequestMethod.POST)
    public String doSearch(@RequestParam(value = "number", required = false) String number,
                           @RequestParam(value = "login", required = false) String login,
                           Locale locale, Model model) {
        User user;
        try {
            if (number == null || number.equals("")) {
                user = userService.getUserByLogin(login);
            }
            else {
                long userNumber = Long.parseLong(Parser.doParse(number));
                user = userService.getUserByNumber(userNumber);
            }

            model.addAttribute("id", user.getId());
            return "redirect:/cp_employee_user_data_change";
        }
        catch (Exception ex) {
            model.addAttribute("found", "false");
            return "cp_employee/cp_employee_user_search";
        }
    }

}
