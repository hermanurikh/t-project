package ru.tsystems.tproject.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tsystems.tproject.entities.Contract;
import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.entities.Tariff;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.services.API.ContractService;
import ru.tsystems.tproject.services.API.OptionService;
import ru.tsystems.tproject.services.API.TariffService;
import ru.tsystems.tproject.services.API.UserService;

import java.util.List;

/**
 * This class will handle the logic when an entity is removed,
 */
@Service("entityRemoval")
public class EntityRemoval {
    @Autowired
    private TariffService tariffService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private UserService userService;
    @Autowired
    private OptionService optionService;

    private static final int BASE_TARIFF = 11;
    private static final int TARIFF_COMPENSATION = 500;
    private static final int OPTION_COMPENSATION = 100;

    /**
     * When a tariff is removed, all users that have it in contract have the tariff changed to base one. Their balance is increased
     * by 500.
     * @param tariffId the tariff's id
     */
    public void removeTariff(int tariffId) {
        User user;
        Tariff tariff = tariffService.getEntityById(tariffId);
        Tariff baseTariff = tariffService.getEntityById(BASE_TARIFF);
        List<Contract> contractList = contractService.getAll();
        for (Contract x : contractList) {
            if (x.getTariff().equals(tariff)) {
                x.setTariff(baseTariff);
                user = x.getUser();
                user.setBalance(user.getBalance() + TARIFF_COMPENSATION);
                contractService.updateEntity(x);
                userService.updateEntity(user);
            }
        }
        tariffService.deleteEntity(tariff);
    }

    /**
     * When an option is removed, all users that used to have this option get +100 on their balance.
     * @param optionId the option's id
     */
    public void removeOption(int optionId) {
        Option option = optionService.getEntityById(optionId);
        option.getOptionsTogether().clear();
        option.getOptionsIncompatible().clear();
        User user;
        boolean flag = false;
        List<Option> options = optionService.getAll();
        for (Option x : options) {
            if (x.getOptionsTogether().contains(option)) {
                x.getOptionsTogether().remove(option);
                flag = true;
            }
            if (x.getOptionsIncompatible().contains(option)) {
                x.getOptionsIncompatible().remove(option);
                flag = true;
            }
            if (flag) {
                optionService.updateEntity(x);
                flag = false;
            }
        }
        List<Tariff> tariffs = tariffService.getAll();
        for (Tariff x : tariffs) {
            if (x.getPossibleOptions().contains(option)) {
                x.removeOptionForTariff(option);
                tariffService.updateEntity(x);
            }
        }
        List<Contract> contractList = contractService.getAll();
        for (Contract x : contractList) {
            if (x.getOptions().contains(option)) {
                x.removeOption(option);
                contractService.updateEntity(x);
                user = x.getUser();
                user.setBalance(user.getBalance() + OPTION_COMPENSATION);
                userService.updateEntity(user);
            }
        }
        optionService.deleteEntity(option);
    }
}
