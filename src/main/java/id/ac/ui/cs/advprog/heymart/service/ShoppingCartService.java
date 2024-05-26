package id.ac.ui.cs.advprog.heymart.service;

public interface ShoppingCartService {
    boolean addToCart(String username, String productId);

    boolean removeFromCart(String username, String productId);

    boolean checkout(String username, String productId);
}
