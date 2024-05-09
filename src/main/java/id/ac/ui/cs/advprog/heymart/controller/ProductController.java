package id.ac.ui.cs.advprog.heymart.controller;

import id.ac.ui.cs.advprog.heymart.model.Product;
import id.ac.ui.cs.advprog.heymart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all-products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable Long productId,
            @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(productId, product.getName(), product.getPrice(), product.getQuantity());
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/listProduct")
    public String listProduct(Model model, Principal principal) {
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
        return "listProduct"; // Assuming the name of your Thymeleaf template is 'listProduct.html'
    }






}
