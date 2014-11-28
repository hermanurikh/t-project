package ru.tsystems.tproject.integration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.tsystems.tproject.entities.*;
import ru.tsystems.tproject.services.API.ContractService;
import ru.tsystems.tproject.services.API.OptionService;
import ru.tsystems.tproject.services.API.TariffService;
import ru.tsystems.tproject.services.API.UserService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

/**
 * A class to hold the EntityRemoval class tests.
 */
@RunWith(MockitoJUnitRunner.class)
public class EntityRemovalTest {
    @InjectMocks
    private EntityRemoval entityRemoval;
    @Mock
    private ContractService contractService;
    @Mock
    private UserService userService;
    @Mock
    private TariffService tariffService;
    @Mock
    private OptionService optionService;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String DATE = "1992-04-14";
    private static final String PASSPORT = "passport";
    private static final String ADDRESS = "address";
    private static final String EMAIL = "email";
    private static final String LOGIN = "login";
    private static final Integer BALANCE = 0;
    private static final String PASSWORD = "password";
    private static final String ROLE = "User";
    private List<Contract> contractList = new ArrayList<>();
    private List<Option> optionList = new ArrayList<>();
    private List<Tariff> tariffList = new ArrayList<>();
    private static final String BASE_TARIFF = "baseTariff";
    private static final int BASE_TARIFF_ID = 11;
    private static final String NAME_ONE = "test1";
    private static final String NAME_TWO = "test2";
    private static final int ID_ONE = 1;
    private static final int PRICE = 5;
    private static final int INITIAL_PRICE = 50;
    private static final int TARIFF_COMPENSATION = 500;
    private static final int OPTION_COMPENSATION = 100;


    @Before
    public void init() throws Exception {
        Tariff baseTariff = new Tariff(BASE_TARIFF, PRICE);
        when(tariffService.getEntityById(BASE_TARIFF_ID)).thenReturn(baseTariff);
        when(contractService.getAll()).thenReturn(contractList);
        when(optionService.getAll()).thenReturn(optionList);
        when(tariffService.getAll()).thenReturn(tariffList);
    }
    @Test
    public void removeTariffTest() throws Exception{
        Tariff tariff1 = new Tariff(NAME_ONE, PRICE);
        Tariff tariff2 = new Tariff(NAME_TWO, PRICE);
        tariff1.setId(ID_ONE);
        tariff2.setId(2);
        when(tariffService.getEntityById(ID_ONE)).thenReturn(tariff1);
        User user = new User(NAME, SURNAME, DATE_FORMAT.parse(DATE), PASSPORT, ADDRESS, EMAIL, LOGIN, BALANCE, PASSWORD,new Role(ROLE));
        Contract contract1 = new Contract(9l, user, tariff1);
        Contract contract2 = new Contract(92l, user, tariff1);
        Contract contract3 = new Contract(91l, user, tariff2);
        doNothing().when(contractService).updateEntity(contract1);
        doNothing().when(contractService).updateEntity(contract2);
        doNothing().when(contractService).updateEntity(contract3);
        doNothing().when(userService).updateEntity(user);
        doNothing().when(tariffService).deleteEntity(tariff1);
        Collections.addAll(contractList, contract1, contract2, contract3);
        entityRemoval.removeTariff(ID_ONE);
        assertTrue(user.getBalance() == 2 * TARIFF_COMPENSATION);
        assertTrue(contractList.get(0).getTariff().equals(tariffService.getEntityById(BASE_TARIFF_ID)));
        assertTrue(contractList.get(ID_ONE).getTariff().equals(tariffService.getEntityById(BASE_TARIFF_ID)));
        assertFalse(contractList.get(2).getTariff().equals(tariffService.getEntityById(BASE_TARIFF_ID)));
    }
    @Test
    public void removeOptionTest() throws Exception {
        Tariff tariff1 = new Tariff(NAME_ONE, PRICE);
        Tariff tariff2 = new Tariff(NAME_TWO, PRICE);
        Option option1 = new Option(NAME_ONE, PRICE, INITIAL_PRICE);
        Option option2 = new Option(NAME_TWO, PRICE, INITIAL_PRICE);
        option1.setId(ID_ONE);
        option2.setId(2);
        User user = new User(NAME, SURNAME, DATE_FORMAT.parse(DATE), PASSPORT, ADDRESS, EMAIL, LOGIN, BALANCE, PASSWORD,new Role(ROLE));
        Contract contract1 = new Contract(9l, user, tariff1);
        Contract contract2 = new Contract(92l, user, tariff1);
        contract1.addOption(option1);
        contract2.addOption(option1);
        option2.addOptionsTogether(option1);
        tariff2.addPossibleOption(option1);
        Collections.addAll(optionList, option1, option2);
        Collections.addAll(tariffList, tariff1, tariff2);
        Collections.addAll(contractList, contract1, contract2);
        when(optionService.getEntityById(ID_ONE)).thenReturn(option1);
        doNothing().when(optionService).updateEntity(option2);
        doNothing().when(tariffService).updateEntity(tariff2);
        doNothing().when(contractService).updateEntity(contract1);
        doNothing().when(contractService).updateEntity(contract2);
        doNothing().when(userService).updateEntity(user);
        doNothing().when(optionService).deleteEntity(option1);
        entityRemoval.removeOption(ID_ONE);
        assertTrue(option2.getOptionsTogether().isEmpty());
        assertTrue(tariff2.getPossibleOptions().isEmpty());
        assertTrue(contract1.getOptions().isEmpty());
        assertTrue(contract2.getOptions().isEmpty());
        assertTrue(user.getBalance() == 2 * OPTION_COMPENSATION);
    }
}
