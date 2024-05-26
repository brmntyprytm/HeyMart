package id.ac.ui.cs.advprog.heymart.controller;

import id.ac.ui.cs.advprog.heymart.model.Product;
import id.ac.ui.cs.advprog.heymart.model.User;
import id.ac.ui.cs.advprog.heymart.repository.UserRepository;
import id.ac.ui.cs.advprog.heymart.service.*;
import id.ac.ui.cs.advprog.heymart.validator.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserValidator userValidator;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ProductService productService;

    @MockBean
    private ShoppingCartService shoppingCartService;

    @MockBean
    private SupermarketService supermarketService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLandingPage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("landing"));
    }

    @Test
    public void testRegisterPage() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    public void testLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void testRegisterUserSuccess() throws Exception {
        when(userService.registerUser(any(User.class))).thenReturn(true);

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "testuser")
                        .param("password", "password")
                        .param("email", "testuser@example.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"))
                .andExpect(model().attributeExists("success"));

        verify(userService, times(1)).registerUser(any(User.class));
    }

    @Test
    public void testRegisterUserFailure() throws Exception {
        when(userService.registerUser(any(User.class))).thenReturn(false);

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "testuser")
                        .param("password", "password")
                        .param("email", "testuser@example.com"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attributeExists("message"));

        verify(userService, times(1)).registerUser(any(User.class));
    }

    @Test
    public void testLoginUserSuccess() throws Exception {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setRole("user");
        when(userService.authenticate(anyString(), anyString())).thenReturn(user);

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "testuser")
                        .param("password", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/listProductUser"));

        verify(userService, times(1)).authenticate(anyString(), anyString());
    }

    @Test
    public void testLoginUserFailure() throws Exception {
        when(userService.authenticate(anyString(), anyString())).thenReturn(null);

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "testuser")
                        .param("password", "wrongpassword"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/login"))
                .andExpect(flash().attributeExists("error"));

        verify(userService, times(1)).authenticate(anyString(), anyString());
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    public void testGreetingPage() throws Exception {
        User user = new User();
        user.setUsername("testuser");
        user.setBalance(100.0);

        when(userRepository.findByUsername(anyString())).thenReturn(user);
        when(productService.getAllProducts()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/listProductUser"))
                .andExpect(status().isOk())
                .andExpect(view().name("listProductUser"))
                .andExpect(model().attributeExists("username"))
                .andExpect(model().attributeExists("role"))
                .andExpect(model().attributeExists("balance"))
                .andExpect(model().attributeExists("products"));

        verify(userRepository, times(1)).findByUsername(anyString());
        verify(productService, times(1)).getAllProducts();
    }
}
