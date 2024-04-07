package id.ac.ui.cs.advprog.heymart.model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class BalanceModelTest {
    
    @Test
    void testBalanceConstructor(){
        Balance balance = new Balance(1L, 727 );
        assertEquals(1L, balance.getId());
        assertEquals(1L, balance.getBalance());
    }
}
