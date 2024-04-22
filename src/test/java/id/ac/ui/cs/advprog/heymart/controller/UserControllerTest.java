package id.ac.ui.cs.advprog.heymart.controller;

import id.ac.ui.cs.advprog.heymart.model.User;
import id.ac.ui.cs.advprog.heymart.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLandingPage() {
        assertEquals("landing", userController.landingPage());
    }

    @Test
    public void testRegisterPage() {
        assertEquals("register", userController.registerPage(model));
    }

    @Test
    public void testLoginPage() {
        assertEquals("login", userController.loginPage(model));
    }

    @Test
    public void testRegisterUserSuccess() {
        User user = new User();
        when(userService.registerUser(user)).thenReturn(true);
        assertEquals("redirect:/login", userController.registerUser(user, model));
        verify(userService, times(1)).registerUser(user);
    }

    @Test
    public void testRegisterUserFailure() {
        User user = new User();
        when(userService.registerUser(user)).thenReturn(false);
        assertEquals("register", userController.registerUser(user, model));
        verify(userService, times(1)).registerUser(user);
        verify(model, times(1)).addAttribute("message", "Registration failed. Please try again.");
    }

    @Test
    public void testLoginUserSuccess() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password");
        when(userService.loginUser("testUser", "password")).thenReturn(true);
        assertEquals("redirect:/home", userController.loginUser(user, model, redirectAttributes));
        verify(userService, times(1)).loginUser("testUser", "password");
        verify(redirectAttributes, times(1)).addFlashAttribute("username", "testUser");
    }

    @Test
    public void testLoginUserFailure() {
        User user = new User();
        when(userService.loginUser(user.getUsername(), user.getPassword())).thenReturn(false);
        assertEquals("login", userController.loginUser(user, model, redirectAttributes));
        verify(userService, times(1)).loginUser(user.getUsername(), user.getPassword());
        verify(model, times(1)).addAttribute("message", "Login failed. Please try again.");
    }

    @Test
    public void testGreetingPage() {
        assertEquals("home", userController.greetingPage(model));
    }
}
