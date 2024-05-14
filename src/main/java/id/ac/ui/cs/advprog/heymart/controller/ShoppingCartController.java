package id.ac.ui.cs.advprog.heymart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import id.ac.ui.cs.advprog.heymart.service.ShoppingCartService;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public RedirectView addToCart(@RequestParam String username, @RequestParam String productId) {
        boolean added = shoppingCartService.addToCart(username, productId);
        if (added) {
            return new RedirectView("/listProductUser");
        } else {
            return new RedirectView("/listProductUser");
        }
    }

    @PostMapping("/remove")
    public RedirectView removeFromCart(@RequestParam String username, @RequestParam String productId) {
        boolean removed = shoppingCartService.removeFromCart(username, productId);
        if (removed) {
            return new RedirectView("/shoppingCart");
        } else {
            return new RedirectView("/shoppingCart");
        }
    }
}
