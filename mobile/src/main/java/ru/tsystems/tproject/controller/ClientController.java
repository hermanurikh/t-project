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
import ru.tsystems.tproject.services.API.ContractService;
import ru.tsystems.tproject.services.API.OptionService;
import ru.tsystems.tproject.services.API.TariffService;
import ru.tsystems.tproject.services.API.UserService;

import javax.servlet.http.HttpServletRequest;
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
        model.addAttribute("contractsUserList", user.getContracts());
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
        List<Option> optionList = contract.getOptions();
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
            }
            /*else {
                model.addAttribute("paramIsBlocked", "ВКЛЮЧЕНА АДМИНИСТРАТОРОМ. Вы не можете самостоятельно снять блокировку или произвести изменения с контрактом. Пожалуйста, обратитесь к администратору");
            }*/
        }
        else {
            contract.setBlocked(true);
            contractService.updateEntity(contract);
            request.getSession().setAttribute("paramIsBlocked", "ВКЛЮЧЕНА. Вы не можете произвести изменения с контрактом");
            request.getSession().setAttribute("action", "Разблокировать");
        }
        return "cp_client/cp_client_change_contract";
    }
    @RequestMapping(value = "/cp_client_contract_change_options", method = RequestMethod.GET)
    public String selectOptions(@RequestParam(value = "cb") int tariffId,
                                HttpServletRequest request, Locale locale, Model model) {
        if (((Contract) request.getSession().getAttribute("contract")).isBlocked()) {
            return "cp_client/cp_client_change_contract";
        }
        model.addAttribute("optionsList", optionService.getAllOptionsForTariff(tariffId));
        return "cp_client/cp_client_contract_change_options";
    }





}
