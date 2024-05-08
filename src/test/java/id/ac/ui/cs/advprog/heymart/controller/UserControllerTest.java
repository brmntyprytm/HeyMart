package id.ac.ui.cs.advprog.heymart.controller;

import id.ac.ui.cs.advprog.heymart.model.User;
import id.ac.ui.cs.advprog.heymart.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void testLoginUser_Success() {
        // Arrange
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");

        when(userService.loginUser("testuser", "password")).thenReturn(true);

        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

        // Act
        String viewName = userController.loginUser(user, null, redirectAttributes);

        // Assert
        assertEquals("redirect:/home", viewName);
        verify(redirectAttributes, times(1)).addFlashAttribute("username", "testuser");
    }

    @Test
    void testGreetingPage() {
        // Act
        String viewName = userController.greetingPage(null);

        // Assert
        assertEquals("home", viewName);
    }
}
