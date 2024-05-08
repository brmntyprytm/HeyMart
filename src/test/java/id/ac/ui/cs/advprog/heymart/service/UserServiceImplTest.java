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
    public void testRegisterUserSuccess() {
        User user = new User();
        user.setUsername("testUser");
        user.setRole("user");

        when(userRepository.existsByUsername("testUser")).thenReturn(false);
        when(userRepository.save(user)).thenReturn(user);

        assertTrue(userService.registerUser(user));
        verify(userRepository, times(1)).existsByUsername("testUser");
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testRegisterUserWithExistingUsername() {
        User user = new User();
        user.setUsername("existingUser");
        user.setRole("user");

        when(userRepository.existsByUsername("existingUser")).thenReturn(true);

        assertFalse(userService.registerUser(user));
        verify(userRepository, times(1)).existsByUsername("existingUser");
        verify(userRepository, never()).save(user);
    }

    @Test
    public void testRegisterUserWithAdminRole() {
        User user = new User();
        user.setUsername("testAdmin");
        user.setRole("user");

        when(userRepository.existsByUsername("testAdmin")).thenReturn(false);
        when(userRepository.save(user)).thenReturn(user);

        user.setRole("admin");

        assertTrue(userService.registerUser(user));
        verify(userRepository, times(1)).existsByUsername("testAdmin");
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testRegisterUserWithManagerRole() {
        User user = new User();
        user.setUsername("testManager");
        user.setRole("manager");

        when(userRepository.existsByUsername("testManager")).thenReturn(false);
        when(userRepository.save(user)).thenReturn(user);

        assertTrue(userService.registerUser(user));
        verify(userRepository, times(1)).existsByUsername("testManager");
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testRegisterUserWithException() {
        User user = new User();
        user.setUsername("testUser");
        user.setRole("user");

        when(userRepository.existsByUsername("testUser")).thenReturn(false);
        when(userRepository.save(user)).thenThrow(new RuntimeException("Database connection failed"));

        assertFalse(userService.registerUser(user));
        verify(userRepository, times(1)).existsByUsername("testUser");
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testLoginUser_Successful() {
        when(userRepository.findByUsername("test_user")).thenReturn(new User(null, "test_user", "password123", "test@example.com", "Manager"));

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
        when(userRepository.findByUsername("test_user")).thenReturn(new User(null, "test_user", "password123", "test@example.com", "Manager"));

        assertFalse(userService.loginUser("test_user", "invalid_password"));

        verify(userRepository, times(1)).findByUsername("test_user");
    }
}