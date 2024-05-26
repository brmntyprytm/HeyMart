package id.ac.ui.cs.advprog.heymart.controller;

import id.ac.ui.cs.advprog.heymart.repository.ProductRepository;
import id.ac.ui.cs.advprog.heymart.repository.UserRepository;
import id.ac.ui.cs.advprog.heymart.repository.ShoppingCartRepository;
import id.ac.ui.cs.advprog.heymart.model.Product;
import id.ac.ui.cs.advprog.heymart.model.ShoppingCart;
import id.ac.ui.cs.advprog.heymart.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import id.ac.ui.cs.advprog.heymart.service.ShoppingCartService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public ModelAndView addToCart(@RequestParam String username, @RequestParam String productId, Model model) {
        boolean added = shoppingCartService.addToCart(username, productId);
        if (!added) {
            model.addAttribute("warning", "Your cart contains products from a different supermarket. Do you want to replace them?");
            model.addAttribute("username", username);
            model.addAttribute("productId", productId);
            return new ModelAndView("confirmReplace", model.asMap());
        }
        return new ModelAndView("redirect:/listProductUser");
    }


    @PostMapping("/confirmReplace")
    public ModelAndView confirmReplace(@RequestParam String username, @RequestParam String productId) {
        User user = userRepository.findByUsername(username);
        Product product = productRepository.findById(productId).orElse(null);

        if (user == null || product == null) {
            return new ModelAndView("error"); // User or product not found
        }

        ShoppingCart shoppingCart = user.getShoppingCart();
        shoppingCart.getProducts().clear();
        shoppingCart.addProduct(product);
        shoppingCartRepository.save(shoppingCart);

        return new ModelAndView("redirect:/listProductUser");
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

    @PostMapping("/checkout")
    public RedirectView checkout(@RequestParam String username, @RequestParam String productId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        boolean checkedOut = shoppingCartService.checkout(username, productId);
        if (checkedOut) {
            Double updatedBalance = userRepository.findByUsername(username).getBalance();
            session.setAttribute("balance", updatedBalance);
        }
        return new RedirectView("/shoppingCart");
    }
}
