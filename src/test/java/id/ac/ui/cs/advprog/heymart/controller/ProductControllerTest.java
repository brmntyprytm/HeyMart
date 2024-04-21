package id.ac.ui.cs.advprog.heymart.controller;

import id.ac.ui.cs.advprog.heymart.model.Product;
import id.ac.ui.cs.advprog.heymart.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllProducts() {
        // Mocking ProductService behavior
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Product1", 10.0, 5));
        productList.add(new Product("Product2", 15.0, 8));
        when(productService.getAllProducts()).thenReturn(productList);

        // Call the controller method
        ResponseEntity<List<Product>> responseEntity = productController.getAllProducts();

        // Verify the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
        assertEquals("Product1", responseEntity.getBody().get(0).getName());
        assertEquals(10.0, responseEntity.getBody().get(0).getPrice());
        assertEquals(5, responseEntity.getBody().get(0).getQuantity());
        assertEquals("Product2", responseEntity.getBody().get(1).getName());
        assertEquals(15.0, responseEntity.getBody().get(1).getPrice());
        assertEquals(8, responseEntity.getBody().get(1).getQuantity());
    }

    @Test
    public void testCreateProduct() {
        // Arrange
        Product product = new Product();
        product.setName("Test Product");

        when(productService.createProduct(product)).thenReturn(product);

        // Act
        ResponseEntity<Product> responseEntity = productController.createProduct(product);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(product, responseEntity.getBody());
        verify(productService, times(1)).createProduct(product);
    }

    @Test
    public void testUpdateProduct() {
        // Arrange
        Long productId = 1L;
        String updatedName = "Updated Product";
        Double updatedPrice = 25.0;
        Integer updatedQuantity = 50;
        Product updatedProduct = new Product();
        updatedProduct.setId(productId);
        updatedProduct.setName(updatedName);
        updatedProduct.setPrice(updatedPrice);
        updatedProduct.setQuantity(updatedQuantity);

        when(productService.updateProduct(productId, updatedName, updatedPrice, updatedQuantity))
                .thenReturn(updatedProduct);

        // Act
        ResponseEntity<Product> responseEntity = productController.updateProduct(productId, updatedName, updatedPrice, updatedQuantity);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedProduct, responseEntity.getBody());
        verify(productService, times(1)).updateProduct(productId, updatedName, updatedPrice, updatedQuantity);
    }
}
