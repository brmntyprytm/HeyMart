package id.ac.ui.cs.advprog.heymart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import id.ac.ui.cs.advprog.heymart.model.User;
import id.ac.ui.cs.advprog.heymart.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import id.ac.ui.cs.advprog.heymart.repository.ShoppingCartRepository;
import id.ac.ui.cs.advprog.heymart.model.ShoppingCart;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return false;
        }
        if (user.getShoppingCart() == null) {
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setUser(user);
            user.setShoppingCart(shoppingCart);
        }

        return user.getPassword().equals(password);

    }

    @Transactional
    public boolean registerUser(User user) {
        // Check if username already exists
        if (userRepository.existsByUsername(user.getUsername())) {
            return false;
        }

        // Validate that password and confirmPassword match
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return false;
        }

        // Hash the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Assign role
        if ("admin".equals(user.getUsername())) {
            user.setRole("admin");
        } else if ("manager".equals(user.getRole())) {
            user.setRole("manager");
        } else {
            user.setRole("user");
        }

        // Initialize balance
        user.setBalance(0.0);

        try {
            // Save user to the database
            userRepository.save(user);

            // Initialize and save shopping cart
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setUser(user);
            shoppingCartRepository.save(shoppingCart);

            // Associate shopping cart with user
            user.setShoppingCart(shoppingCart);
            userRepository.save(user);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public User authenticate(String username, String password) {
        return userRepository.findByUsername(username);
    }
}
