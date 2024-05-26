package id.ac.ui.cs.advprog.heymart.model;

import id.ac.ui.cs.advprog.heymart.model.Product;
import id.ac.ui.cs.advprog.heymart.model.ShoppingCart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import static org.junit.jupiter.api.Assertions.*;


public class ShoppingCartModelTest {

    @Test
    public void testAddProduct() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Product product1 = new Product();
        Product product2 = new Product();

        shoppingCart.addProduct(product1);
        shoppingCart.addProduct(product2);

        assertEquals(2, shoppingCart.getProducts().size());
        assertTrue(shoppingCart.getProducts().contains(product1));
        assertTrue(shoppingCart.getProducts().contains(product2));
    }

    @Test
    public void testRemoveProduct() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Product product1 = new Product();
        Product product2 = new Product();

        shoppingCart.addProduct(product1);
        shoppingCart.addProduct(product2);

        shoppingCart.removeProduct(product1);

        assertEquals(1, shoppingCart.getProducts().size());
        assertFalse(shoppingCart.getProducts().contains(product1));
        assertTrue(shoppingCart.getProducts().contains(product2));
    }

    @Test
    public void testIsFromSameSupermarket_WhenEmpty() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Product product = new Product();
        assertTrue(shoppingCart.isFromSameSupermarket(product));
    }

    @Test
    public void testIsFromSameSupermarket_WhenSameSupermarket() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Supermarket supermarket = new Supermarket();
        Product product1 = new Product();
        Product product2 = new Product();
        product1.setSupermarket(supermarket);
        product2.setSupermarket(supermarket);

        shoppingCart.addProduct(product1);

        assertTrue(shoppingCart.isFromSameSupermarket(product2));
    }

    @Test
    public void testIsFromSameSupermarket_WhenDifferentSupermarket() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Supermarket supermarket1 = new Supermarket();
        Supermarket supermarket2 = new Supermarket();
        Product product1 = new Product();
        Product product2 = new Product();
        product1.setSupermarket(supermarket1);
        product2.setSupermarket(supermarket2);

        shoppingCart.addProduct(product1);

        assertFalse(shoppingCart.isFromSameSupermarket(product2));
    }
}
