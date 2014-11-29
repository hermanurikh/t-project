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
import org.springframework.web.util.NestedServletException;
import ru.tsystems.tproject.services.API.ContractService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.tsystems.tproject.utils.pages.ClientPages.*;

/**
 * A class to test the ClientController.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring.xml")
@WebAppConfiguration
public class ClientControllerTest {
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
                .andExpect(forwardedUrl(CLIENT + MAIN));
    }
    @Test
    public void testProfilePage() throws Exception {
        mockMvc.perform(get(HELPER + PROFILE))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(CLIENT + PROFILE));
    }
    @Test (expected = NestedServletException.class)
    public void testContractsPage() throws Exception {

        mockMvc.perform(get(HELPER + CONTRACTS));
    }
    @Test
    public void testBalancePage() throws Exception {
        mockMvc.perform(get(HELPER + BALANCE))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl(CLIENT + BALANCE));
    }




}
