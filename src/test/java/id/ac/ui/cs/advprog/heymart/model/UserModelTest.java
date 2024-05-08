package id.ac.ui.cs.advprog.heymart.model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserModelTest {

    @Test
    void testUserConstructorAndGetters() {
        User user = new User(1L, "john_doe", "password123", "john.doe@example.com", "Manager");

        assertEquals(1L, user.getId());
        assertEquals("john_doe", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals("john.doe@example.com", user.getEmail());
    }

    @Test
    void testUserToString() {
        User user = new User(1L, "john_doe", "password123", "john.doe@example.com", "Manager");

        assertNotNull(user.toString());
    }
}

