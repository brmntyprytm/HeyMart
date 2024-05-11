package id.ac.ui.cs.advprog.heymart.controller;

import id.ac.ui.cs.advprog.heymart.model.Product;
import id.ac.ui.cs.advprog.heymart.model.User;
import id.ac.ui.cs.advprog.heymart.repository.UserRepository;
import id.ac.ui.cs.advprog.heymart.service.UserService;
import id.ac.ui.cs.advprog.heymart.service.ProductService;
import id.ac.ui.cs.advprog.heymart.validator.UserValidator;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;

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

            // Set success attribute for displaying success message
            redirectAttributes.addFlashAttribute("success", true);

            // Redirect based on user role
            if ("manager".equalsIgnoreCase(loggedInUser.getRole())) {
                return "redirect:/listProductManager"; // Redirect manager to manager's home page
            } else if ("admin".equalsIgnoreCase(loggedInUser.getRole())) {
                return "redirect:/adminHome"; // Redirect admin to admin's home page
            } else {
                return "redirect:/listProductUser"; // Redirect regular user to home page
            }
        } else {
            // Set error attribute for displaying error message
            redirectAttributes.addFlashAttribute("error", true);
            return "redirect:/login"; // Redirect back to login page
        }
    }


    @GetMapping("/listProductUser")
    public String greetingPage(@ModelAttribute("username") String username, @ModelAttribute("role") String role, Model model) {
        model.addAttribute("username", username);
        model.addAttribute("role", role);

        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        return "listProductUser";
    }

    @GetMapping("/managerHome")
    public String managerPage(@ModelAttribute("username") String username, @ModelAttribute("role") String role, Model model) {
        model.addAttribute("username", username);
        model.addAttribute("role", role);
        return "managerHome";
    }

    @GetMapping("/adminHome")
    public String adminPage(@ModelAttribute("username") String username, @ModelAttribute("role") String role, Model model) {
        model.addAttribute("username", username);
        model.addAttribute("role", role);
        return "adminHome";
    }

    @PostMapping("/logout")
    public String logoutUser(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.clearContext();

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    break;
                }
            }
        }

        return "redirect:/";
    }

    @GetMapping("/listProductManager")
    public String listProductManager(Model model, Principal principal) {
        String username = null;
        String role = "USER"; // Default role if not available

        if (principal != null) {
            username = principal.getName();
            // Assuming you have a method to retrieve the user's role, replace "getUserRole()" with the actual method
            // For example: role = userService.getUserRole(username);
            // Here, userService is the service responsible for user-related operations
        }

        model.addAttribute("username", username);
        model.addAttribute("role", role);

        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        // Returning the name of the HTML template containing the table
        return "listProductManager"; // Assuming the name of your Thymeleaf template is 'listProductManager.html'
    }
}


