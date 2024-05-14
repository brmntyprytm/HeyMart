package id.ac.ui.cs.advprog.heymart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import id.ac.ui.cs.advprog.heymart.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public String addToCart(@RequestParam String username, @RequestParam String productId) {
        boolean added = shoppingCartService.addToCart(username, productId);
        if (added) {
            return "Product added to cart successfully";
        } else {
            return "Failed to add product to cart";
        }
    }

    @PostMapping("/remove")
    public ResponseEntity<String> removeFromCart(@RequestParam String username, @RequestParam String productId) {
        boolean removed = shoppingCartService.removeFromCart(username, productId);
        if (removed) {
            return ResponseEntity.ok("Product removed from cart successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to remove product from cart");
        }
    }
}
