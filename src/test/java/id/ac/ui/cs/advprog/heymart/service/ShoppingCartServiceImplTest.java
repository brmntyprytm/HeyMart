package id.ac.ui.cs.advprog.heymart.service;

import id.ac.ui.cs.advprog.heymart.model.Product;
import id.ac.ui.cs.advprog.heymart.model.ShoppingCart;
import id.ac.ui.cs.advprog.heymart.model.User;
import id.ac.ui.cs.advprog.heymart.repository.ProductRepository;
import id.ac.ui.cs.advprog.heymart.repository.ShoppingCartRepository;
import id.ac.ui.cs.advprog.heymart.repository.UserRepository;
import id.ac.ui.cs.advprog.heymart.service.ShoppingCartService;
import id.ac.ui.cs.advprog.heymart.service.ShoppingCartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ShoppingCartServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @InjectMocks
    private ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

}
