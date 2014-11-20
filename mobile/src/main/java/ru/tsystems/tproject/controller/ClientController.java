
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
import ru.tsystems.tproject.services.API.ContractService;
import ru.tsystems.tproject.services.API.OptionService;
import ru.tsystems.tproject.services.API.TariffService;
import ru.tsystems.tproject.services.API.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A controller to dispatch the queries of the clients.
 */
@Controller
public class ClientController {
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

    /**
     * This method returns a main page for the user control panel.
     * @param locale locale;
     * @param model model;
     * @return cp_client_main.jsp
     */
    @RequestMapping(value = "/cp_client_main", method = RequestMethod.GET)
    public String getMainPage(Locale locale, Model model) {
        return "cp_client/cp_client_main";
    }

    /**
     * This method returns a profile page.
     * @param locale locale;
     * @param model model;
     * @return cp_client_profile.jsp
     */
    @RequestMapping(value = "/cp_client_profile", method = RequestMethod.GET)
    public String getProfile(Locale locale, Model model) {
        return "cp_client/cp_client_profile";
    }

    /**
     * This method returns a page will all the contracts of current user.
     * @param request request;
     * @param locale locale;
     * @param model model;
     * @return cp_client_contracts.jsp
     */
    @RequestMapping(value = "/cp_client_contracts", method = RequestMethod.GET)
    public String getContracts(HttpServletRequest request, Locale locale, Model model) {
        User user = (User) request.getSession().getAttribute("currentUserU");
        model.addAttribute("contractsUserList", contractService.getAllContractsForUser(user.getId()));
        return "cp_client/cp_client_contracts";
    }

    /**
     * This method returns a page where the detailed info about the contract can be viewed.
     * @param contractId the contracts id;
     * @param locale locale;
     * @param model model;
     * @return cp_client_contract_details.jsp
     */
    @RequestMapping(value = "/cp_client_contract_details", method = RequestMethod.GET)
    public String getContractDetails(@RequestParam(value = "contractId") int contractId,
                                     HttpServletRequest request, Locale locale, Model model) {
        Contract contract = contractService.getEntityById(contractId);
        User user = (User) request.getSession().getAttribute("currentUserU");
        if (!user.getContracts().contains(contract)) return "cp_client/cp_client_main";
        int amount = contract.getTariff().getPrice();
        List<Option> optionList = optionService.getAllOptionsForContract(contractId);
        if (!optionList.isEmpty()) {
            for (Option x : optionList) {
                amount += x.getPrice();
            }
        }
        model.addAttribute("contract", contract);
        model.addAttribute("optionsList", optionList);
        model.addAttribute("totalAmount", amount);
        if (contract.isBlocked()) {
            if (contract.getEmployee() != null) {
                model.addAttribute("isItBlocked", "ЗАБЛОКИРОВАН АДМИНИСТРАТОРОМ");
            }
            else {
                model.addAttribute("isItBlocked", "ЗАБЛОКИРОВАН");
            }
        }
        else {
            model.addAttribute("isItBlocked", "активен");
        }
        return "cp_client/cp_client_contract_details";
    }

    /**
     * This method returns a page where the tariff for the contract should be selected.
     * @param contractId the contracts id;
     * @param request request;
     * @param locale locale;
     * @param model model;
     * @return cp_client_change_contract.jsp
     */
    @RequestMapping(value = "/cp_client_change_contract", method = RequestMethod.GET)
    public String changeContract(@RequestParam(value = "contractId") int contractId,
                                 HttpServletRequest request, Locale locale, Model model) {
        Contract contract = contractService.getEntityById(contractId);
        User user = (User) request.getSession().getAttribute("currentUserU");
        if (!user.getContracts().contains(contract)) return "cp_client/cp_client_main";
        if (contract.isBlocked()) {
            if (contract.getEmployee() != null) {
                request.getSession().setAttribute("paramIsBlocked", "ВКЛЮЧЕНА АДМИНИСТРАТОРОМ. Вы не можете самостоятельно разблокировать контракт. Пожалуйста, обратитесь к администратору");
            }
            else {
                request.getSession().setAttribute("paramIsBlocked", "ВКЛЮЧЕНА");
                request.getSession().setAttribute("action", "Разблокировать");
            }
        }
        else {
            request.getSession().setAttribute("paramIsBlocked", "выключена");
            request.getSession().setAttribute("action", "Заблокировать");
        }
        request.getSession().setAttribute("tariffsList", tariffService.getAll());
        request.getSession().setAttribute("contract", contract);
        /*request.getSession().setAttribute("contractNumber", contract.getNumber());*/
        return "cp_client/cp_client_change_contract";
    }

    /**
     * This method:
     *              - blocks a contract, if it is not blocked;
     *              - unblocks a contract, if it had been blocked by user;
     *              - doesn't allow to do any actions, if it has been blocked by employee.
     * @param number contracts number;
     * @param request request;
     * @param locale locale;
     * @param model model;
     * @return cp_client_change_contract.jsp
     */
    @RequestMapping(value = "/cp_client_block_contract", method = RequestMethod.GET)
    public String blockContract(@RequestParam(value = "contractNumber") long number,
                                HttpServletRequest request, Locale locale, Model model) {
        Contract contract = contractService.getContractByNumber(number);
        User user = (User) request.getSession().getAttribute("currentUserU");
        if (!user.getContracts().contains(contract)) return "cp_client/cp_client_main";
        if (contract.isBlocked()) {
            if (contract.getEmployee() == null) {
                contract.setBlocked(false);
                contractService.updateEntity(contract);
                request.getSession().setAttribute("paramIsBlocked", "выключена");
                request.getSession().setAttribute("action", "Заблокировать");
                request.getSession().setAttribute("contract", contract);
            }
        }
        else {
            contract.setBlocked(true);
            contractService.updateEntity(contract);
            request.getSession().setAttribute("paramIsBlocked", "ВКЛЮЧЕНА. Вы не можете произвести изменения с контрактом");
            request.getSession().setAttribute("action", "Разблокировать");
            request.getSession().setAttribute("contract", contract);
        }
        return "cp_client/cp_client_change_contract";
    }

    /**
     * This method returns a page where the options for the contract are selected. If the contract is blocked, it redirects back to
     * the page with the tariff selection.
     * @param tariffId tariff's id;
     * @param request request;
     * @param locale locale;
     * @param model model;
     * @return cp_client_contract_change_options.jsp or cp_client_change_contract.jsp
     */
    @RequestMapping(value = "/cp_client_contract_change_options", method = RequestMethod.GET)
    public String selectOptions(@RequestParam(value = "cb") int tariffId,
                                HttpServletRequest request, Locale locale, Model model) {
        if (((Contract) request.getSession().getAttribute("contract")).isBlocked()) {
            return "cp_client/cp_client_change_contract";
        }
        model.addAttribute("optionsList", optionService.getAllOptionsForTariff(tariffId));
        request.getSession().setAttribute("tariffId", tariffId);
        return "cp_client/cp_client_contract_change_options";
    }

    /** This method returns a page with a bucket with selected options, if the entered data is valid, and redirects back to the page with options otherwise.
     * The array with options' id is validated by a contractValidator. If something is incorrect, the exception is
     * added to the exceptionsList.
     * @param array the array of options' ids;
     * @param request request;
     * @param locale locale;
     * @param model model;
     * @return cp_client_contract_change_bucket.jsp or cp_client_contract_change_options.jsp
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/cp_client_contract_bucket", method = RequestMethod.POST)
    public String finalContractChange(@RequestParam(value = "cb", required = false) int[] array,
                                      HttpServletRequest request, Locale locale, Model model) {
        int tariffId = Integer.parseInt(String.valueOf(request.getSession().getAttribute("tariffId")));;
        Tariff tariff = tariffService.getEntityById(tariffId);
        Contract contract = (Contract) request.getSession().getAttribute("contract");
        contract.setTariff(tariff);
        if (array == null || array.length == 0) {
            contract.removeAllOptions();
            request.getSession().setAttribute("updatedContract", contract);
            model.addAttribute("optionsList", contract.getOptions());
            return "cp_client/cp_client_contract_change_bucket";
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
                request.getSession().setAttribute("updatedContract", contract);
                model.addAttribute("optionsList", contract.getOptions());
                return "cp_client/cp_client_contract_change_bucket";
            }
            else {
                model.addAttribute("optionsList", optionService.getAllOptionsForTariff(tariffId));
                model.addAttribute("areExceptions", "true");
                model.addAttribute("exceptionsList", exceptionList);
                return "cp_client/cp_client_contract_change_options";
            }
        }


    }

    /**
     * This method finally changes the contract after the approval by the client.
     * @param request request;
     * @param locale locale;
     * @param model model;
     * @return success.jsp
     */
    @RequestMapping(value = "/cp_client_bucket_approved", method =  RequestMethod.POST)
    public String approveBucket(HttpServletRequest request, Locale locale, Model model) {
        Contract contract = (Contract) request.getSession().getAttribute("updatedContract");
        User user = (User) request.getSession().getAttribute("currentUserU");
        contractService.updateEntity(contract);
        userService.updateEntity(user);
        return "cp_client/success";
    }

    /**
     * This method returns a page where you can increase the current balance.
     * @param locale locale;
     * @param model model;
     * @return cp_client_balance
     */
    @RequestMapping(value = "/cp_client_balance", method = RequestMethod.GET)
    public String getBalance(Locale locale, Model model) {
        return "cp_client/cp_client_balance";
    }

    /**
     * This method increases the current balance by 100.
     * @param request request;
     * @param locale locale;
     * @param model model;
     * @return cp_client_balance.jsp
     */
    @RequestMapping(value = "/cp_client_increase_balance", method = RequestMethod.GET)
    public String increaseBalance(HttpServletRequest request, Locale locale, Model model) {
        User user = (User) request.getSession().getAttribute("currentUserU");
        user.setBalance(user.getBalance() + 100);
        userService.updateEntity(user);
        request.getSession().setAttribute("currentUserU", user);
        return "cp_client/cp_client_balance";
    }
}

