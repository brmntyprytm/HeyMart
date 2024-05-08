package id.ac.ui.cs.advprog.heymart.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import id.ac.ui.cs.advprog.heymart.controller.UserController;
import id.ac.ui.cs.advprog.heymart.model.User;
import id.ac.ui.cs.advprog.heymart.repository.UserRepository;
import id.ac.ui.cs.advprog.heymart.service.UserService;
import id.ac.ui.cs.advprog.heymart.validator.UserValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserValidator userValidator;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private UserController userController;

    @Test
    void landingPage_ReturnsCorrectViewName() {
        String viewName = userController.landingPage();
        assertThat(viewName).isEqualTo("landing");
    }


    @Test
    void loginPage_ReturnsCorrectViewName() {
        String viewName = userController.loginPage(model);
        assertThat(viewName).isEqualTo("login");
    }

    @Test
    void registerUser_WithValidUser_RedirectsToLoginPage() {
        User user = new User();
        when(userService.registerUser(any(User.class))).thenReturn(true);

        String viewName = userController.registerUser(user, bindingResult, model);

        assertThat(viewName).isEqualTo("redirect:/login");
        verify(userService).registerUser(user);
    }

    @Test
    void registerUser_WithInvalidUser_ReturnsRegisterPage() {
        User user = new User();
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = userController.registerUser(user, bindingResult, model);

        assertThat(viewName).isEqualTo("register");
        verify(userValidator).validate(user, bindingResult);
    }

    @Test
    void loginUser_WithValidCredentials_RedirectsToHomePage() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("password");
        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        when(userService.loginUser(user.getUsername(), user.getPassword())).thenReturn(true);

        String viewName = userController.loginUser(user, model, redirectAttributes);

        assertThat(viewName).isEqualTo("redirect:/home");
        verify(redirectAttributes).addFlashAttribute("username", user.getUsername());
        verify(redirectAttributes).addFlashAttribute("role", user.getRole());
    }

    @Test
    void loginUser_WithInvalidCredentials_ReturnsLoginPage() {
        User user = new User();
        when(userService.loginUser(user.getUsername(), user.getPassword())).thenReturn(false);

        String viewName = userController.loginUser(user, model, redirectAttributes);

        assertThat(viewName).isEqualTo("login");
        verify(model).addAttribute("message", "Login failed. Please try again.");
    }

    @Test
    void greetingPage_ReturnsCorrectViewName() {
        String viewName = userController.greetingPage("test", "user", model);
        assertThat(viewName).isEqualTo("home");
        verify(model).addAttribute("username", "test");
        verify(model).addAttribute("role", "user");
    }

    @Test
    void managerPage_ReturnsCorrectViewName() {
        String viewName = userController.managerPage("test", "manager", model);
        assertThat(viewName).isEqualTo("managerHome");
        verify(model).addAttribute("username", "test");
        verify(model).addAttribute("role", "manager");
    }
}