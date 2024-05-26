package id.ac.ui.cs.advprog.heymart.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserModelTest {

    @Test
    public void testUserConstructorAndGetters() {
        // Test the constructor
        User user = new User(1L, "testuser", "password", "testuser@example.com", "user");

        // Test the getters
        assertEquals(1L, user.getId());
        assertEquals("testuser", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals("testuser@example.com", user.getEmail());
        assertEquals("user", user.getRole());
        assertEquals(0.0, user.getBalance());
    }

    @Test
    public void testSetters() {
        User user = new User();
        user.setId(2L);
        user.setUsername("newuser");
        user.setPassword("newpassword");
        user.setEmail("newuser@example.com");
        user.setRole("admin");
        user.setBalance(100.0);

        assertEquals(2L, user.getId());
        assertEquals("newuser", user.getUsername());
        assertEquals("newpassword", user.getPassword());
        assertEquals("newuser@example.com", user.getEmail());
        assertEquals("admin", user.getRole());
        assertEquals(100.0, user.getBalance());
    }

    @Test
    public void testToString() {
        User user = new User(1L, "testuser", "password", "testuser@example.com", "user");
        user.setBalance(50.0);
        String expected = "User{id=1, username='testuser', password='password', email='testuser@example.com', role='user', balance='50.0'}";
        assertEquals(expected, user.toString());
    }

    @Test
    public void testTransientField() {
        User user = new User();
        user.setConfirmPassword("confirmPassword");
        assertEquals("confirmPassword", user.getConfirmPassword());
    }
}
