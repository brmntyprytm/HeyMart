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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
}
