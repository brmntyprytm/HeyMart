package id.ac.ui.cs.advprog.heymart.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroceryTest {

    @Test
    public void testGettersAndSetters() {
        // Create a Grocery object
        Grocery grocery = new Grocery();
        grocery.setId(1L);
        grocery.setName("Apple");
        grocery.setPrice(1.50);
        grocery.setQuantity(10);

        // Test getters
        assertEquals(1L, grocery.getId());
        assertEquals("Apple", grocery.getName());
        assertEquals(1.50, grocery.getPrice());
        assertEquals(10, grocery.getQuantity());

        // Test setters
        grocery.setId(2L);
        grocery.setName("Banana");
        grocery.setPrice(0.75);
        grocery.setQuantity(20);

        assertEquals(2L, grocery.getId());
        assertEquals("Banana", grocery.getName());
        assertEquals(0.75, grocery.getPrice());
        assertEquals(20, grocery.getQuantity());
    }

    @Test
    public void testToString() {
        // Create a Grocery object
        Grocery grocery = new Grocery(1L, "Orange", 1.20, 15);

        // Test toString method
        assertEquals("Grocery{id=1, name='Orange', price=1.2, quantity=15}", grocery.toString());
    }
}

