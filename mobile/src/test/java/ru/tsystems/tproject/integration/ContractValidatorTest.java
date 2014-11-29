package ru.tsystems.tproject.integration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.entities.Role;
import ru.tsystems.tproject.entities.User;
import ru.tsystems.tproject.services.API.OptionService;
import ru.tsystems.tproject.services.API.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


/**
 * A class to test the ContractValidator class.
 */
@RunWith(MockitoJUnitRunner.class)
public class ContractValidatorTest {
    @InjectMocks
    private ContractValidator contractValidator;
    @Mock
    private OptionService optionService;
    @Mock
    private UserService userService;

    private List<Option> optionList = new ArrayList<>();


    @Before
    public void init() {
        Option option1 = new Option(1, "test1", 100, 400);
        Option option2 = new Option(2, "test2", 200, 300);
        Option option3 = new Option(3, "test3", 300, 200);
        Option option4 = new Option(4, "test4", 400, 100);
        option1.addOptionsTogether(option2);
        option3.addOptionsTogether(option4);
        option3.addOptionsIncompatible(option2);
        Collections.addAll(optionList, option1, option2, option3, option4);
        when(optionService.getEntityById(1)).thenReturn(optionList.get(0));
        when(optionService.getEntityById(2)).thenReturn(optionList.get(1));
        when(optionService.getEntityById(3)).thenReturn(optionList.get(2));
        when(optionService.getEntityById(4)).thenReturn(optionList.get(3));
        User user = new User("test", "test", new Date(), "test","test", "test", "test", 300, "test", new Role());
        User user2 = new User("test", "test", new Date(), "test","test", "test", "test", 1000, "test", new Role());
        when(userService.getEntityById(1)).thenReturn(user);
        when(userService.getEntityById(2)).thenReturn(user2);
    }
    @SuppressWarnings("unchecked")
    @Test
    public void testValidateOptionsNoExceptions() {
        int[] array = new int[2];
        array[0] = 1;
        array[1] = 2;
        List<Exception> exceptions = new ArrayList<>();
        List list = contractValidator.validateOptions(array, exceptions, 0);
        exceptions = (List<Exception>) list.get(1);
        assertTrue(exceptions.isEmpty());
    }
    @SuppressWarnings("unchecked")
    @Test
    public void testValidateOptionsOneException() {
        int[] array = new int[3];
        array[0] = 2;
        array[1] = 3;
        array[2] = 4;
        List<Exception> exceptions = new ArrayList<>();
        List list = contractValidator.validateOptions(array, exceptions, 0);
        exceptions = (List<Exception>) list.get(1);
        assertTrue(exceptions.size() == 1);
    }
    @SuppressWarnings("unchecked")
    @Test
    public void testValidateOptionsTwoExceptions() {
        int[] array = new int[3];
        array[0] = 1;
        array[1] = 2;
        array[2] = 3;
        List<Exception> exceptions = new ArrayList<>();
        List list = contractValidator.validateOptions(array, exceptions, 0);
        exceptions = (List<Exception>) list.get(1);
        assertTrue(exceptions.size() == 2);
    }
    @Test
    public void testBalanceCheckPositive() {
        int balance = contractValidator.balanceCheck(2, optionList);
        assertTrue(balance >= 0);
    }
    @Test
    public void testBalanceCheckNegative() {
        int balance = contractValidator.balanceCheck(1, optionList);
        assertTrue(balance < 0);
    }
    @Test
    public void priceCheckTestPositive() throws Exception {
        contractValidator.priceCheck(40000, "price");
    }
    @Test
    public void priceCheckTestPositive2() throws Exception {
        contractValidator.priceCheck(0, "price");
    }
    @Test(expected = IOException.class)
    public void priceCheckTestNegative() throws Exception  {
        contractValidator.priceCheck(400001, "price");
    }
    @Test(expected = IOException.class)
    public void priceCheckTestNegative2() throws Exception  {
        contractValidator.priceCheck(-1, "price");
    }

}
