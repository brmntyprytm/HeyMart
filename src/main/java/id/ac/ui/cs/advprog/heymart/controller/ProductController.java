package id.ac.ui.cs.advprog.heymart.controller;

import id.ac.ui.cs.advprog.heymart.model.Product;
import id.ac.ui.cs.advprog.heymart.repository.ProductRepository;
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
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

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
            @PathVariable String productId,
            @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(productId, product.getName(), product.getPrice(), product.getQuantity());
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable String productId) {
        // Find the product by ID
        Optional<Product> productOptional = productRepository.findById(productId);
        // Check if product exists
        if (productOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        // Get the product from Optional
        Product product = productOptional.get();
        // Delete the product
        productRepository.delete(product);
        return ResponseEntity.ok().build();
    }

}
