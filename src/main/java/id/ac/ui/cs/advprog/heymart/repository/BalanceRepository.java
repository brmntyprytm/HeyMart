package id.ac.ui.cs.advprog.heymart.repository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import id.ac.ui.cs.advprog.heymart.repository.UserRepository;
import id.ac.ui.cs.advprog.heymart.model.User;
import id.ac.ui.cs.advprog.heymart.model.Balance;
import java.util.List;
import java.util.ArrayList;

@Repository
public class BalanceRepository {
    private List<Balance> balances = new ArrayList<>();

    @Autowired
    private UserRepository userRepository;

    public void add(Balance balance) {
        balances.add(balance);
    }

    public Balance findById(Long Id) {
        for (Balance b : balances) {
            if (b.getId().equals(Id)) {
                return b;
            }
        }
        return null;
    }

    @Transactional
    public double incrementBalance(String username, double amount) {
//        for (Balance b: balances) {
//            if (b.getId().equals(id)) {
//                b.setBalance(b.getBalance() + amount);
//                return b.getBalance();
//            }
//        }
        User user = userRepository.findByUsername(username);
        double balanceLog = user.getBalance();
        System.out.println(balanceLog);
        user.setBalance(user.getBalance() + amount);
        double balanceLog2 = user.getBalance();
        System.out.println(balanceLog2);
        System.out.println("repository incrementBalance executed");
        return -1;
    }

    public double decrementBalance(Long id, double amount) {
        for (Balance b: balances) {
            if (b.getId().equals(id)) {
                if (b.getBalance() - amount >= 0) {
                    b.setBalance(b.getBalance() - amount);
                    return b.getBalance();
                }
                return -2;
            }
        }
        return -1;
    }
}
