package id.ac.ui.cs.advprog.heymart.controller;

import id.ac.ui.cs.advprog.heymart.model.User;
import id.ac.ui.cs.advprog.heymart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

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
    public String registerUser(@ModelAttribute User user, Model model) {
        boolean isRegistered = userService.registerUser(user);
        if (isRegistered) {
            return "redirect:/login";
        } else {
            model.addAttribute("message", "Registration failed. Please try again.");
            return "register";
        }
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model, RedirectAttributes redirectAttributes) {
        boolean isLoggedIn = userService.loginUser(user.getUsername(), user.getPassword());
        if (isLoggedIn) {
            redirectAttributes.addFlashAttribute("username", user.getUsername());
            return "redirect:/greeting";
        } else {
            model.addAttribute("message", "Login failed. Please try again.");
            return "login";
        }
    }

    @GetMapping("/greeting")
    public String greetingPage(Model model) {
        return "greeting";
    }
}
