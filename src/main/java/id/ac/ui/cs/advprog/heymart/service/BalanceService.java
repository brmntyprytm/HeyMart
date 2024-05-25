package id.ac.ui.cs.advprog.heymart.service;

import id.ac.ui.cs.advprog.heymart.model.Balance;

public interface BalanceService {
    double topUp(String username, double amount);
    double withdraw(Long id, double amount);
    boolean checkoutUpdate(String username, Long shopId, double amount);
}
