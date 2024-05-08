package id.ac.ui.cs.advprog.heymart.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    @Test
    void testConstructorAndGetters() {
        // Create a Product object using the constructor
        Product product = new Product("Test Product", 10.0, 5);

        // Verify that the constructor correctly initializes the fields
        assertEquals("Test Product", product.getName());
        assertEquals(10.0, product.getPrice());
        assertEquals(5, product.getQuantity());
    }

    @Test
    void testSetters() {
        // Create a Product object
        Product product = new Product();

        // Set values using setter methods
        product.setName("New Product");
        product.setPrice(15.0);
        product.setQuantity(8);

        // Verify that the setter methods correctly set the values
        assertEquals("New Product", product.getName());
        assertEquals(15.0, product.getPrice());
        assertEquals(8, product.getQuantity());
    }

    @Test
    void testToString() {
        // Create a Product object
        Product product = new Product("Test Product", 10.0, 5);

        // Verify that the toString method returns the expected string representation
        assertEquals("Product{id=null, name='Test Product', price=10.0, quantity=5}", product.toString());
    }
}
