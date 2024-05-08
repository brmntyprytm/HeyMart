package id.ac.ui.cs.advprog.heymart.service;

import id.ac.ui.cs.advprog.heymart.model.Product;
import id.ac.ui.cs.advprog.heymart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        // Save the product to the database using the repository
        return productRepository.save(product);
    }

    public Product updateProduct(Long productId, String name, Double price, Integer quantity) {
        // Retrieve the product from the database
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()) {
            // Update the attributes of the product
            Product product = optionalProduct.get();
            product.setName(name);
            product.setPrice(price);
            product.setQuantity(quantity);

            // Save the updated product to the database
            return productRepository.save(product);
        } else {
            // If the product does not exist, return null
            return null;
        }
    }


    public void deleteProduct(Long productId) {
        // Delete the product from the database using the repository
        productRepository.deleteById(productId);
    }
}
