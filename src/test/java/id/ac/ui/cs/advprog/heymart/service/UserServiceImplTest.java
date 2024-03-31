package id.ac.ui.cs.advprog.heymart.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import id.ac.ui.cs.advprog.heymart.model.User;
import id.ac.ui.cs.advprog.heymart.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser_Successful() {
        User user = new User(null, "test_user", "password123", "test@example.com");

        when(userRepository.existsByUsername("test_user")).thenReturn(false);
        when(userRepository.save(user)).thenReturn(user);

        assertTrue(userService.registerUser(user));

        verify(userRepository, times(1)).existsByUsername("test_user");
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testRegisterUser_Failure_UsernameExists() {
        User user = new User(null, "existing_user", "password123", "test@example.com");

        when(userRepository.existsByUsername("existing_user")).thenReturn(true);

        assertFalse(userService.registerUser(user));

        verify(userRepository, times(1)).existsByUsername("existing_user");
        verify(userRepository, never()).save(user);
    }

    @Test
    void testLoginUser_Successful() {
        when(userRepository.findByUsername("test_user")).thenReturn(new User(null, "test_user", "password123", "test@example.com"));

        assertTrue(userService.loginUser("test_user", "password123"));

        verify(userRepository, times(1)).findByUsername("test_user");
    }

    @Test
    void testLoginUser_Failure_InvalidUsername() {
        when(userRepository.findByUsername("unknown_user")).thenReturn(null);

        assertFalse(userService.loginUser("unknown_user", "password123"));

        verify(userRepository, times(1)).findByUsername("unknown_user");
    }

    @Test
    void testLoginUser_Failure_InvalidPassword() {
        when(userRepository.findByUsername("test_user")).thenReturn(new User(null, "test_user", "password123", "test@example.com"));

        assertFalse(userService.loginUser("test_user", "invalid_password"));

        verify(userRepository, times(1)).findByUsername("test_user");
    }
}