package id.ac.ui.cs.advprog.heymart.repository;
import id.ac.ui.cs.advprog.heymart.model.Supermarket;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import id.ac.ui.cs.advprog.heymart.repository.UserRepository;
import id.ac.ui.cs.advprog.heymart.model.User;
import id.ac.ui.cs.advprog.heymart.model.Balance;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public class BalanceRepository {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SupermarketRepository supermarketRepository;

    @Transactional
    public double incrementUserBalance(String username, double amount) {
        User user = userRepository.findByUsername(username);
        user.setBalance(user.getBalance() + amount);
        return -1;
    }

    @Transactional
    public double decrementUserBalance(String username, double amount) {
        User user = userRepository.findByUsername(username);
        user.setBalance(user.getBalance() - amount);
        return -1;
    }

    @Transactional
    public double incrementShopBalance(Long id, double amount) {
        Supermarket supermarket = supermarketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supermarket not found with id: " + id));
        supermarket.setBalance(supermarket.getBalance() + amount);
        return -1;
    }

    @Transactional
    public double decrementShopBalance(Long id, double amount) {
        Supermarket supermarket = supermarketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supermarket not found with id: " + id));
        supermarket.setBalance(supermarket.getBalance() - amount);
        return -1;
    }

}
