package id.ac.ui.cs.advprog.heymart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import id.ac.ui.cs.advprog.heymart.service.ShoppingCartService;

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
}
