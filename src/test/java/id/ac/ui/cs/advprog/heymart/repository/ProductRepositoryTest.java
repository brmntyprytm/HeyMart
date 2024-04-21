package id.ac.ui.cs.advprog.heymart.repository;

import id.ac.ui.cs.advprog.heymart.model.Product;
import id.ac.ui.cs.advprog.heymart.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void testFindAll() {
        // Create a list of products for mocking repository behavior
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Product1", 10.0, 5));
        productList.add(new Product("Product2", 15.0, 8));

        // Mocking repository behavior
        when(productRepository.findAll()).thenReturn(productList);

        // Call the service method
        List<Product> retrievedProducts = productService.getAllProducts();

        // Verify the result
        assertEquals(2, retrievedProducts.size());
        assertEquals("Product1", retrievedProducts.get(0).getName());
        assertEquals(10.0, retrievedProducts.get(0).getPrice());
        assertEquals(5, retrievedProducts.get(0).getQuantity());
        assertEquals("Product2", retrievedProducts.get(1).getName());
        assertEquals(15.0, retrievedProducts.get(1).getPrice());
        assertEquals(8, retrievedProducts.get(1).getQuantity());
    }

    @Test
    void testCreateProduct() {
        // Create a new product
        Product product = new Product("Test Product", 10.0, 100);

        // Mocking repository behavior
        when(productRepository.save(any())).thenReturn(product);

        // Call the service method to create the product
        Product savedProduct = productService.createProduct(product);

        // Verify that the saved product is not null
        assertNotNull(savedProduct);

        // Verify that the saved product has the correct attributes
        assertEquals("Test Product", savedProduct.getName());
        assertEquals(10.0, savedProduct.getPrice());
        assertEquals(100, savedProduct.getQuantity());
    }

    @Test
    void testUpdateProduct() {
        // Create a product with initial data
        Product initialProduct = new Product("Initial Product", 10.0, 50);

        // Mocking repository behavior to return the initial product when saving
        when(productRepository.save(any())).thenReturn(initialProduct);
        when(productRepository.findById(any())).thenReturn(Optional.of(initialProduct));

        // Call the service method to update the product
        Product updatedProduct = productService.updateProduct(initialProduct.getId(), "Updated Product", 15.0, 100);

        // Verify that the updated product is not null
        assertNotNull(updatedProduct);

        // Verify that the updated product has the correct attributes
        assertEquals("Updated Product", updatedProduct.getName());
        assertEquals(15.0, updatedProduct.getPrice());
        assertEquals(100, updatedProduct.getQuantity());
    }

    @Test
    void testDeleteProduct() {
        // Create a product with a known ID
        Long productId = 1L;

        // Mocking repository behavior
        when(productRepository.findById(productId)).thenReturn(Optional.of(new Product()));

        // Call the service method to delete the product
        productService.deleteProduct(productId);

        // Verify that the delete method of the repository is called with the correct ID
        verify(productRepository).deleteById(productId);
    }
}
