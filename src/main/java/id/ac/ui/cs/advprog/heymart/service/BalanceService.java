package id.ac.ui.cs.advprog.heymart.service;

import id.ac.ui.cs.advprog.heymart.model.Balance;

public interface BalanceService {
    Balance add(Balance balance);
    Balance findById(Long id);
    double topUp(String username, double amount);
    double withdraw(Long id, double amount);
    boolean checkoutUpdate();
}
