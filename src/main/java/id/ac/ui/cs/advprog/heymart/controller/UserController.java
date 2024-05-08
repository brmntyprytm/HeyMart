package id.ac.ui.cs.advprog.heymart.controller;

import id.ac.ui.cs.advprog.heymart.model.User;
import id.ac.ui.cs.advprog.heymart.repository.UserRepository;
import id.ac.ui.cs.advprog.heymart.service.UserService;
import id.ac.ui.cs.advprog.heymart.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String landingPage() {
        return "landing";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute @Validated User user, BindingResult bindingResult, Model model) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "register";
        }

        boolean isRegistered = userService.registerUser(user);
        if (isRegistered) {
            model.addAttribute("success", true);
            return "redirect:/login";
        } else {
            model.addAttribute("error", true);
            model.addAttribute("message", "Registration failed. Please try again.");
            return "register";
        }
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model, RedirectAttributes redirectAttributes) {
        boolean isLoggedIn = userService.loginUser(user.getUsername(), user.getPassword());
        if (isLoggedIn) {
            User loggedInUser = userRepository.findByUsername(user.getUsername());
            redirectAttributes.addFlashAttribute("username", loggedInUser.getUsername());
            redirectAttributes.addFlashAttribute("role", loggedInUser.getRole());

            if ("manager".equalsIgnoreCase(loggedInUser.getRole())) {
                return "redirect:/managerHome"; // Redirect manager to manager's home page
            } else {
                return "redirect:/home"; // Redirect regular user to home page
            }
        } else {
            model.addAttribute("message", "Login failed. Please try again.");
            return "login";
        }
    }

    @GetMapping("/home")
    public String greetingPage(@ModelAttribute("username") String username, @ModelAttribute("role") String role, Model model) {
        model.addAttribute("username", username);
        model.addAttribute("role", role);
        return "home";
    }

    @GetMapping("/managerHome")
    public String managerPage(@ModelAttribute("username") String username, @ModelAttribute("role") String role, Model model) {
        model.addAttribute("username", username);
        model.addAttribute("role", role);
        return "managerHome";
    }
}


