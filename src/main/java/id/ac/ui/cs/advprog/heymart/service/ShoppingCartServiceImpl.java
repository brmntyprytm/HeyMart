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

    @Override
    public boolean checkout(String username, String productId) {
        User user = userRepository.findByUsername(username);
        Product product = productRepository
                .findById(productId)
                .orElse(null);

        if (user == null || product == null) {
            return false; // User or product not found
        }

        ShoppingCart shoppingCart = user.getShoppingCart();

        if (user.getBalance() > product.getPrice()) {
            shoppingCart.removeProduct(product);
            user.setBalance(user.getBalance() - product.getPrice());
            userRepository.save(user);

            Integer newQuantity = product.getQuantity() - 1;
            product.setQuantity(newQuantity);
            productRepository.save(product);
            return true;

        } else {
            return false; // Insufficient balance
        }
    }
}
