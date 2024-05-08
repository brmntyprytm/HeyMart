package id.ac.ui.cs.advprog.heymart.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import id.ac.ui.cs.advprog.heymart.model.User;
import org.springframework.validation.ValidationUtils;


@Component
public class UserValidator implements Validator {

    private static final String EMAIL_REGEX = "^[\\w.-]+@[\\w-]+(\\.[\\w-]+)+$";

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (!user.getUsername().matches("^[a-zA-Z0-9]+$")) {
            errors.rejectValue("username", "Pattern.user.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (!user.getEmail().matches(EMAIL_REGEX)) {
            errors.rejectValue("email", "Pattern.user.email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8) {
            errors.rejectValue("password", "Size.user.password");
        }
    }
}