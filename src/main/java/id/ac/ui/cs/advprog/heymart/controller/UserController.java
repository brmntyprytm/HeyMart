package id.ac.ui.cs.advprog.heymart.controller;

import id.ac.ui.cs.advprog.heymart.model.Product;
import id.ac.ui.cs.advprog.heymart.model.Supermarket;
import id.ac.ui.cs.advprog.heymart.model.User;
import id.ac.ui.cs.advprog.heymart.repository.UserRepository;
import id.ac.ui.cs.advprog.heymart.service.ShoppingCartService;
import id.ac.ui.cs.advprog.heymart.service.SupermarketService;
import id.ac.ui.cs.advprog.heymart.service.UserService;
import id.ac.ui.cs.advprog.heymart.service.ProductService;
import id.ac.ui.cs.advprog.heymart.validator.UserValidator;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private SupermarketService supermarketService;

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
    public String loginUser(@ModelAttribute User user, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        User loggedInUser = userService.authenticate(user.getUsername(), user.getPassword());
        if (loggedInUser != null) {
            // Store user's information in session
            HttpSession session = request.getSession();
            session.setAttribute("username", loggedInUser.getUsername());
            session.setAttribute("role", loggedInUser.getRole());
            session.setAttribute("balance", loggedInUser.getBalance());
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
    public String greetingPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        // Fetch the updated balance from the database
        Double updatedBalance = userRepository.findByUsername(username).getBalance();
        session.setAttribute("balance", updatedBalance);

        model.addAttribute("username", username);
        model.addAttribute("role", (String) session.getAttribute("role"));
        model.addAttribute("balance", updatedBalance);

        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        return "listProductUser";
    }


    @GetMapping("/managerHome")
    public String managerPage(Model model, HttpServletRequest request) {
        // Retrieve username and role from session
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");

        // Get all supermarkets
        List<Supermarket> supermarkets = supermarketService.getAllSupermarkets();

        // Add username, role, and supermarkets to the model
        model.addAttribute("username", username);
        model.addAttribute("role", role);
        model.addAttribute("supermarkets", supermarkets);

        // Return the view name
        return "managerHome";
    }

    @GetMapping("/adminHome")
    public String adminPage(@ModelAttribute("username") String username, @ModelAttribute("role") String role, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        username = (String) session.getAttribute("username");
        role = (String) session.getAttribute("role");

        model.addAttribute("username", username);
        model.addAttribute("role", role);
        return "adminHome";
    }

    @PostMapping("/logout")
    public String logoutUser(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.clearContext();

        HttpSession session = request.getSession();
        session.removeAttribute("username");
        session.removeAttribute("role");

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
    public String listProductManager(@ModelAttribute("username") String username, @ModelAttribute("role") String role, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        username = (String) session.getAttribute("username");
        role = (String) session.getAttribute("role");

        model.addAttribute("username", username);
        model.addAttribute("role", role);

        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);

        // Returning the name of the HTML template containing the table
        return "listProductManager"; // Assuming the name of your Thymeleaf template is 'listProductManager.html'
    }

    @GetMapping("/shoppingCart")
    public String shoppingCart(@ModelAttribute("username") String username, @ModelAttribute("role") String role, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        username = (String) session.getAttribute("username");
        role = (String) session.getAttribute("role");

        Double totalCost = shoppingCartService.getTotalCost(username);

        model.addAttribute("username", username);
        model.addAttribute("role", role);
        model.addAttribute("totalCost", totalCost);

        User user = userRepository.findByUsername(username);
        model.addAttribute("products", user.getShoppingCart().getProducts()); // Get the list of products from the shopping cart

        // Retrieve message and messageType from the session and add them to the model
        String message = (String) session.getAttribute("message");
        String messageType = (String) session.getAttribute("messageType");

        if (message != null) {
            model.addAttribute("message", message);
            model.addAttribute("messageType", messageType);

            // Remove the attributes from the session to avoid showing the message on subsequent requests
            session.removeAttribute("message");
            session.removeAttribute("messageType");
        }

        return "shoppingCart";
    }


    @GetMapping("/edit-product/{productId}")
    public String editProductPage(@PathVariable String productId, Model model) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            model.addAttribute("product", product);
            return "editProduct";
        } else {
            // If product not found, redirect to error page or handle appropriately
            return "listProductManager"; // Assuming you have an error.html template
        }
    }
}


