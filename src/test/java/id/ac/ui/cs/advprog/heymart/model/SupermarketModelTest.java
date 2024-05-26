package id.ac.ui.cs.advprog.heymart.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SupermarketModelTest {

    @Test
    void testGetDefault() {
        Supermarket defaultSupermarket = Supermarket.getDefault();
        assertNotNull(defaultSupermarket);
        assertEquals(1L, defaultSupermarket.getId());
        assertEquals("not_affiliated", defaultSupermarket.getName());
        assertEquals("default_location", defaultSupermarket.getLocation());
    }

    @Test
    void testToString() {
        Supermarket supermarket = new Supermarket(1L, "Test Mart", "Test Location");
        String str = supermarket.toString();
        assertEquals("Supermarket{id=1, name='Test Mart', location='Test Location'}", str);
    }

    @Test
    void testSetterGetter() {
        Supermarket supermarket = new Supermarket();
        supermarket.setId(1L);
        supermarket.setName("Test Mart");
        supermarket.setLocation("Test Location");
        supermarket.setBalance(1000.0);
        assertEquals(1L, supermarket.getId());
        assertEquals("Test Mart", supermarket.getName());
        assertEquals("Test Location", supermarket.getLocation());
        assertEquals(1000.0, supermarket.getBalance());
    }
}
