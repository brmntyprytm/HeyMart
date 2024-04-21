package id.ac.ui.cs.advprog.heymart.repository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import id.ac.ui.cs.advprog.heymart.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveUser() {
        User user = new User(null, "test_user", "test_password", "test@example.com", "Manager");
        User savedUser = userRepository.save(user);
        assertNotNull(savedUser.getId());

        User retrievedUser = userRepository.findByUsername(savedUser.getUsername());
        assertNotNull(retrievedUser);
        assertEquals(savedUser.getId(), retrievedUser.getId());
        assertEquals("test_user", retrievedUser.getUsername());
        assertEquals("test_password", retrievedUser.getPassword());
        assertEquals("test@example.com", retrievedUser.getEmail());
    }
}
