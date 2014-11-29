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
import ru.tsystems.tproject.services.API.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * A class to test the LoginController.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring.xml")
@WebAppConfiguration
public class LoginControllerTest {
    @Autowired
    WebApplicationContext wac;
    @Mock
    private UserService userService;
    private static MockMvc mockMvc;
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
    @Test
    public void testLogoutPage() throws Exception {
        mockMvc.perform(get("/logout"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("login"));
    }

    @Test
    public void testLoginDeniedPage() throws Exception {
        mockMvc.perform(get("/login-denied"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("isInputValid"))
                .andExpect(forwardedUrl("login"));
    }
    @Test (expected = NestedServletException.class)
    public void testMainPage() throws Exception {
        mockMvc.perform(get("/main"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("login"));
    }
}
