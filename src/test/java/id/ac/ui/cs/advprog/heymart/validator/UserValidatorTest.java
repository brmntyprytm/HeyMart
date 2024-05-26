package id.ac.ui.cs.advprog.heymart.validator;

import id.ac.ui.cs.advprog.heymart.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.*;

public class UserValidatorTest {

    private UserValidator userValidator;
    private User user;
    private Errors errors;

    @BeforeEach
    public void setup() {
        userValidator = new UserValidator();
        user = new User();
        errors = new BeanPropertyBindingResult(user, "user");
    }

    @Test
    public void testSupports() {
        assertTrue(userValidator.supports(User.class));
        assertFalse(userValidator.supports(Object.class));
    }

    @Test
    public void testValidate_ValidUser() {
        user.setUsername("validuser");
        user.setEmail("validuser@example.com");
        user.setPassword("validPassword");

        userValidator.validate(user, errors);

        assertFalse(errors.hasErrors());
    }

    @Test
    public void testValidate_UsernameEmpty() {
        user.setUsername("");
        user.setEmail("validuser@example.com");
        user.setPassword("validPassword");

        userValidator.validate(user, errors);

        assertTrue(errors.hasFieldErrors("username"));
        assertEquals("NotEmpty", errors.getFieldError("username").getCode());
    }

    @Test
    public void testValidate_UsernamePattern() {
        user.setUsername("invalid user");
        user.setEmail("validuser@example.com");
        user.setPassword("validPassword");

        userValidator.validate(user, errors);

        assertTrue(errors.hasFieldErrors("username"));
        assertEquals("Pattern.user.username", errors.getFieldError("username").getCode());
    }

    @Test
    public void testValidate_EmailEmpty() {
        user.setUsername("validuser");
        user.setEmail("");
        user.setPassword("validPassword");

        userValidator.validate(user, errors);

        assertTrue(errors.hasFieldErrors("email"));
        assertEquals("NotEmpty", errors.getFieldError("email").getCode());
    }

    @Test
    public void testValidate_EmailPattern() {
        user.setUsername("validuser");
        user.setEmail("invalid-email");
        user.setPassword("validPassword");

        userValidator.validate(user, errors);

        assertTrue(errors.hasFieldErrors("email"));
        assertEquals("Pattern.user.email", errors.getFieldError("email").getCode());
    }

    @Test
    public void testValidate_PasswordEmpty() {
        user.setUsername("validuser");
        user.setEmail("validuser@example.com");
        user.setPassword("");

        userValidator.validate(user, errors);

        assertTrue(errors.hasFieldErrors("password"));
        assertEquals("NotEmpty", errors.getFieldError("password").getCode());
    }

    @Test
    public void testValidate_PasswordSize() {
        user.setUsername("validuser");
        user.setEmail("validuser@example.com");
        user.setPassword("short");

        userValidator.validate(user, errors);

        assertTrue(errors.hasFieldErrors("password"));
        assertEquals("Size.user.password", errors.getFieldError("password").getCode());
    }
}
