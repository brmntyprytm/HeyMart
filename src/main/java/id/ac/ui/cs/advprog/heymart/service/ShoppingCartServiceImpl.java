package id.ac.ui.cs.advprog.heymart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import id.ac.ui.cs.advprog.heymart.model.Product;
import id.ac.ui.cs.advprog.heymart.model.ShoppingCart;
import id.ac.ui.cs.advprog.heymart.model.User;
import id.ac.ui.cs.advprog.heymart.repository.ProductRepository;
import id.ac.ui.cs.advprog.heymart.repository.ShoppingCartRepository;
import id.ac.ui.cs.advprog.heymart.repository.UserRepository;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    public boolean addToCart(String username, String productId) {
        User user = userRepository.findByUsername(username);
        Product product = productRepository.findById(productId).orElse(null);

        if (user == null || product == null) {
            return false; // User or product not found
        }

        ShoppingCart shoppingCart = user.getShoppingCart();
        if (!shoppingCart.isFromSameSupermarket(product)) {
            return false; // Product is from a different supermarket
        }

        shoppingCart.addProduct(product);
        shoppingCartRepository.save(shoppingCart);
        return true;
    }

    @Override
    public boolean removeFromCart(String username, String productId) {
        User user = userRepository.findByUsername(username);
        Product product = productRepository.findById(productId).orElse(null);

        if (user == null || product == null) {
            return false; // User or product not found
        }

        ShoppingCart shoppingCart = user.getShoppingCart();
        shoppingCart.removeProduct(product);
        shoppingCartRepository.save(shoppingCart);
        return true;
    }

    public boolean checkout(String username) {
        User user = userRepository.findByUsername(username);
        ShoppingCart shoppingCart = user.getShoppingCart();

        if (user == null || shoppingCart == null) {
            return false; // User or shopping cart not found
        }

        double totalCost = shoppingCart.getProducts().stream()
                .mapToDouble(Product::getPrice)
                .sum();

        if (user.getBalance() >= totalCost) {
            for (Product product : shoppingCart.getProducts()) {
                // Update user's balance
                user.setBalance(user.getBalance() - product.getPrice());

                // Decrement all product in shopping cart quantity
                product.setQuantity(product.getQuantity() - 1);
                productRepository.save(product);
            }
            // Clear the shopping cart
            shoppingCart.getProducts().clear();

            // Save the updated user and shopping cart
            userRepository.save(user);
            return true;
        } else {
            return false; // Insufficient balance
        }
    }
}
