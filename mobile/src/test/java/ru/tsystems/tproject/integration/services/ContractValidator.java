package ru.tsystems.tproject.integration.services;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * A class to test the ContractValidator class.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring.xml")
public class ContractValidator {
    @Autowired
    private ru.tsystems.tproject.integration.ContractValidator contractValidator;
    @Test
    public void priceCheckTestPositive() throws Exception {
        contractValidator.priceCheck(40000, "price");
    }
    @Test
    public void priceCheckTestPositive2() throws Exception {
        contractValidator.priceCheck(0, "price");
    }
    @Test(expected = Exception.class)
    public void priceCheckTestNegative() throws Exception  {
        contractValidator.priceCheck(400001, "price");
    }
    @Test(expected = Exception.class)
    public void priceCheckTestNegative2() throws Exception  {
        contractValidator.priceCheck(-1, "price");
    }

}
