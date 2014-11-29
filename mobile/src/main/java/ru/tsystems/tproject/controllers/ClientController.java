
package ru.tsystems.tproject.controllers;
import static ru.tsystems.tproject.utils.pages.ClientPages.*;
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
import ru.tsystems.tproject.exceptions.ScriptViolationException;
import ru.tsystems.tproject.integration.ContractValidator;
import ru.tsystems.tproject.services.API.ContractService;
import ru.tsystems.tproject.services.API.TariffService;
import ru.tsystems.tproject.services.API.UserService;
import ru.tsystems.tproject.utils.locale.Translatable;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * A controllers to dispatch the queries of the clients.
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
    private ContractValidator contractValidator;
    public static final String CURRENT_USER = "currentUserU";
    public static final int AMOUNT = 100;


    /**
     * This method returns a main page for the user control panel.
     * @return cp_client_main.jsp
     */
    @RequestMapping(value = MAIN, method = RequestMethod.GET)
    public String getMainPage() {
        return CLIENT + MAIN;
    }

    /**
     * This method returns a profile page.
     * @return cp_client_profile.jsp
     */
    @RequestMapping(value = PROFILE, method = RequestMethod.GET)
    public String getProfile() {
        return CLIENT + PROFILE;
    }

    /**
     * This method returns a page will all the contracts of current user.
     * @param request request;
     * @param model model;
     * @return cp_client_contracts.jsp
     */
    @RequestMapping(value = CONTRACTS, method = RequestMethod.GET)
    public String getContracts(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute(CURRENT_USER);
        model.addAttribute("contractsUserList", contractService.getAllContractsForUser(user.getId()));
        return CLIENT + CONTRACTS;
    }

    /**
     * This method returns a page where the tariff for the contract should be selected.
     * @param contractId the contracts id;
     * @param request request;
     * @return cp_client_change_contract.jsp
     */
    @RequestMapping(value = CHANGE_CONTRACT, method = RequestMethod.GET)
    public String changeContract(@RequestParam(value = "contractId") int contractId,
                                 HttpServletRequest request) {
        Contract contract = contractService.getEntityById(contractId);
        User user = (User) request.getSession().getAttribute(CURRENT_USER);
        if (!user.getContracts().contains(contract)) return CLIENT + MAIN;
        if (contract.isBlocked()) {
            return CLIENT + MAIN;
        }
        request.getSession().setAttribute("tariffsList", tariffService.getAll());
        request.getSession().setAttribute("contract", contract);
        /*request.getSession().setAttribute("contractNumber", contract.getNumber());*/
        return CLIENT + CHANGE_CONTRACT;
    }

    /**
     * This method:
     *              - blocks a contract, if it is not blocked;
     *              - unblocks a contract, if it had been blocked by user;
     *              - doesn't allow to do any actions, if it has been blocked by employee.
     * @param number contracts number;
     * @param request request;
     * @param model model;
     * @return cp_client_change_contract.jsp
     */
    @RequestMapping(value = BLOCK_CONTRACT, method = RequestMethod.GET)
    public String blockContract(@RequestParam(value = "contractId") int number,
                                HttpServletRequest request, Model model) {
        Translatable translatable = (Translatable) request.getSession().getAttribute("language");
        Contract contract = contractService.getEntityById(number);
        User user = (User) request.getSession().getAttribute(CURRENT_USER);
        if (!user.getContracts().contains(contract)) return CLIENT + MAIN;
        if (contract.isBlocked()) {
            if (contract.getEmployee() == null) {
                contract.setBlocked(false);
                contractService.updateEntity(contract);
                request.getSession().setAttribute("paramIsBlocked", translatable.getJSP_CONTRACTS_UNBLOCKED());
                request.getSession().setAttribute("action", translatable.getJSP_CONTRACTS_BLOCK());
                request.getSession().setAttribute("contract", contract);
            }
        } else {
            contract.setBlocked(true);
            contractService.updateEntity(contract);
            request.getSession().setAttribute("paramIsBlocked", translatable.getJSP_CONTRACTS_BLOCKED());
            request.getSession().setAttribute("action", translatable.getJSP_CONTRACTS_UNBLOCK());
            request.getSession().setAttribute("contract", contract);
        }
        model.addAttribute("contractsUserList", contractService.getAllContractsForUser(user.getId()));
        return CLIENT + CONTRACTS;
    }


    /** This method returns a page with a bucket with selected options, if the entered data is valid, and redirects back to the page with options otherwise.
     * The array with options' id is validated by a contractValidator. If something is incorrect, the exception is
     * added to the exceptionsList.
     * @param array the array of options' ids;
     * @param request request;
     * @param model model;
     * @return cp_client_contract_change_bucket.jsp or exception.jsp
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = BUCKET, method = RequestMethod.POST)
    public String finalContractChange(@RequestParam(value = "cb") int tariffId,
                                      @RequestParam(value = "cb3", required = false) int[] array,
                                      HttpServletRequest request, Model model) throws Exception{
        if (((Contract) request.getSession().getAttribute("contract")).isBlocked()) {
            return CLIENT + MAIN;
        }
        User user = (User) request.getSession().getAttribute(CURRENT_USER);
        Tariff tariff = tariffService.getEntityById(tariffId);
        Contract contract = (Contract) request.getSession().getAttribute("contract");
        contract.setTariff(tariff);
        if (array == null || array.length == 0) {
            contract.removeAllOptions();
            request.getSession().setAttribute("updatedContract", contract);
            model.addAttribute("optionsList", contract.getOptions());
            request.getSession().setAttribute("totalAmount", 0);
            return CLIENT + BUCKET;
        } else {
            List<Exception> exceptionList = new ArrayList<>();
            List validationResultList = contractValidator.validateOptions(array, exceptionList, user.getId()); //checking if the entered options are correct
            List<Option> optionList = (List<Option>) validationResultList.get(0);
            exceptionList = (List<Exception>) validationResultList.get(1);
            if (exceptionList.isEmpty()) {
                contract.removeAllOptions();
                for (Option x : optionList) {
                    contract.addOption(x);
                }
                //the amount to decrease
                request.getSession().setAttribute("totalAmount", user.getBalance() - contractValidator.balanceCheck(user.getId(), optionList));
                request.getSession().setAttribute("updatedContract", contract);
                model.addAttribute("optionsList", contract.getOptions());
                return CLIENT + BUCKET;
            } else {
                throw new ScriptViolationException("jQuery required to perform this operation!");
            }
        }


    }

    /**
     * This method finally changes the contract after the approval by the client.
     * @param request request;
     * @return success.jsp
     */
    @RequestMapping(value = BUCKET_APPROVED, method =  RequestMethod.POST)
    public String approveBucket(HttpServletRequest request) {
        Contract contract = (Contract) request.getSession().getAttribute("updatedContract");
        User user = (User) request.getSession().getAttribute(CURRENT_USER);
        contractService.updateEntity(contract);
        //balance decreased
        user.setBalance(user.getBalance() - (Integer) request.getSession().getAttribute("totalAmount"));
        userService.updateEntity(user);
        return CLIENT + SUCCESS;
    }

    /**
     * This method returns a page where you can increase the current balance.
     * @return cp_client_balance
     */
    @RequestMapping(value = BALANCE, method = RequestMethod.GET)
    public String getBalance() {
        return CLIENT + BALANCE;
    }

    /**
     * This method increases the current balance by 100.
     * @param request request;
     * @return cp_client_balance.jsp
     */
    @RequestMapping(value = INCREASE_BALANCE, method = RequestMethod.GET)
    public String increaseBalance(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(CURRENT_USER);
        user.setBalance(user.getBalance() + AMOUNT);
        userService.updateEntity(user);
        request.getSession().setAttribute(CURRENT_USER, user);
        return CLIENT + BALANCE;
    }
}

