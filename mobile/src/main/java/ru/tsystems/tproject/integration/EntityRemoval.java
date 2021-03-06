package ru.tsystems.tproject.integration;

import org.apache.log4j.Logger;
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
    private static Logger logger = Logger.getLogger(EntityRemoval.class);
    @Autowired
    private TariffService tariffService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private UserService userService;
    @Autowired
    private OptionService optionService;

    /**
     * When a tariff is removed, all users that have it in contract have the tariff changed to base one. Their balance is increased
     * by 500.
     * @param tariffId the tariff's id
     */
    public void removeTariff(int tariffId) {
        int balance = 0;
        User user;
        Tariff tariff = tariffService.getEntityById(tariffId);
        Tariff baseTariff = tariffService.getEntityById(11);
        List<Contract> contractList = contractService.getAll();
        for (Contract x : contractList) {
            if (x.getTariff().equals(tariff)) {
                x.setTariff(baseTariff);
                user = x.getUser();
                user.setBalance(user.getBalance() + 500);
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
        int balance;
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
                user.setBalance(user.getBalance() + 100);
                userService.updateEntity(user);
            }
        }
        optionService.deleteEntity(option);
    }
}
