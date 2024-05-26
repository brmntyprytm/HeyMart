package id.ac.ui.cs.advprog.heymart.repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.heymart.model.Balance;
import id.ac.ui.cs.advprog.heymart.model.User;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class BalanceRepositoryTest {

    @BeforeEach
    void setUp() {
    }
    
//    @Test
//    void findBalanceById() {
//        BalanceRepository balanceRepository = new BalanceRepository();
//        Balance balance1 = new Balance(1L, 727, "Buyer");
//        balanceRepository.add(balance1);
//
//        assertEquals(balance1.getBalance(), balanceRepository.findById(1L).getBalance());
//        assertEquals(balance1.getId(),balanceRepository.findById(1L).getId());
//        assertEquals(balance1.getType(), balanceRepository.findById(1L).getType());
//    }
//
//    @Test
//    void incrementBalanceExistingId() {
//        BalanceRepository balanceRepository = new BalanceRepository();
//        Balance balance1 = new Balance(1L, 727, "Buyer");
//        balanceRepository.add(balance1);
//
//        assertEquals(827, balanceRepository.incrementBalance(1L, 100));
//        assertEquals(827, balanceRepository.findById(1L).getBalance());
//    }
//
//    @Test
//    void incrementBalanceNonExistingId() {
//        BalanceRepository balanceRepository = new BalanceRepository();
//        Balance balance1 = new Balance(1L, 727, "Buyer");
//        balanceRepository.add(balance1);
//
//        assertEquals(-1, balanceRepository.incrementBalance(2L, 100));
//    }
//
//    @Test
//    void decrementBalanceExistingId() {
//        BalanceRepository balanceRepository = new BalanceRepository();
//        Balance balance1 = new Balance(1L, 727, "Buyer");
//        balanceRepository.add(balance1);
//
//        assertEquals(627, balanceRepository.decrementBalance(1L, 100));
//        assertEquals(627, balanceRepository.findById(1L).getBalance());
//    }
//    @Test
//    void decrementBalanceNonExistingId() {
//        BalanceRepository balanceRepository = new BalanceRepository();
//        Balance balance1 = new Balance(1L, 727, "Buyer");
//        balanceRepository.add(balance1);
//
//        assertEquals(-1, balanceRepository.decrementBalance(2L, 100));
//    }
//
//    @Test
//    void decrementbalanceNegativeBalance() {
//        BalanceRepository balanceRepository = new BalanceRepository();
//        Balance balance1 = new Balance(1L, 727, "Buyer");
//        balanceRepository.add(balance1);
//
//        assertEquals(-2, balanceRepository.decrementBalance(1L, 1000));
//    }
}
