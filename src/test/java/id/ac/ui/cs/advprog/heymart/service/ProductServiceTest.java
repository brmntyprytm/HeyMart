package id.ac.ui.cs.advprog.heymart.service;

import id.ac.ui.cs.advprog.heymart.model.Product;
import id.ac.ui.cs.advprog.heymart.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void testGetAllProducts() {
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
}
