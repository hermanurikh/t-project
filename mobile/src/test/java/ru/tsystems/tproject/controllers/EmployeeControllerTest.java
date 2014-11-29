package ru.tsystems.tproject.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.tsystems.tproject.services.API.ContractService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.tsystems.tproject.utils.pages.EmployeePages.*;

/**
 * A class to test the EmployeeController.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring.xml")
@WebAppConfiguration
public class EmployeeControllerTest {
    @Autowired
    WebApplicationContext wac;
    @Mock
    private ContractService contractService;
    private static MockMvc mockMvc;
    private static final String HELPER = "/";
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }


    @Test
    public void testMainPage() throws Exception {
        mockMvc.perform(get(HELPER + MAIN))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(EMPLOYEE + MAIN));
    }
    @Test
    public void testProfilePage() throws Exception {
        mockMvc.perform(get(HELPER + PROFILE))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(EMPLOYEE + PROFILE));
    }
    @Test
    public void testContractsPage() throws Exception {

        mockMvc.perform(get(HELPER + CONTRACTS))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(EMPLOYEE + CONTRACTS));
    }
    @Test
    public void testNewContractPage() throws Exception {
        mockMvc.perform(get(HELPER + NEW_CONTRACT))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(EMPLOYEE + NEW_CONTRACT));
    }
    @Test
    public void testUsersPage() throws Exception {
        mockMvc.perform(get(HELPER + USERS))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(EMPLOYEE + USERS));
    }
    @Test
    public void testUserSearchPage() throws Exception {
        mockMvc.perform(get(HELPER + USER_SEARCH))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(EMPLOYEE + USER_SEARCH));
    }
    @Test
    public void testTariffsPage() throws Exception {
        mockMvc.perform(get(HELPER + TARIFFS))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(EMPLOYEE + TARIFFS));
    }
    @Test
    public void testOptionsPage() throws Exception {
        mockMvc.perform(get(HELPER + OPTIONS))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(EMPLOYEE + OPTIONS));
    }


}
