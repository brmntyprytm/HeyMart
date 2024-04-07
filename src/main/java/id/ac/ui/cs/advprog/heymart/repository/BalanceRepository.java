package id.ac.ui.cs.advprog.heymart.repository;
import org.springframework.stereotype.Repository;
import id.ac.ui.cs.advprog.heymart.model.User;
import id.ac.ui.cs.advprog.heymart.model.Balance;
import java.util.List;
import java.util.ArrayList;

@Repository
public class BalanceRepository {
    private List<Balance> balances = new ArrayList<>();

    public void add(Balance balance) {
        // balances.add(balance);
    }

    public Balance findById(Long Id) {
        // for (Balance b : balances) {
        //     if (b.getId().equals(Id)) {
        //         return b;
        //     }
        // }
        return null;
    }

    public double incrementBalance(Long id, double amount) {
        // for (Balance b: balances) {
        //     if (b.getId().equals(id)) {
        //         b.setBalance(b.getBalance() + amount);
        //         return b.getBalance();
        //     }
        // }
        return -1;
    }

    public double decrementBalance(Long id, double amount) {
        // for (Balance b: balances) {
        //     if (b.getId().equals(id)) {
        //         b.setBalance(b.getBalance() - amount);
        //         return b.getBalance();
        //     }
        // }
        return -1;
    }
}
