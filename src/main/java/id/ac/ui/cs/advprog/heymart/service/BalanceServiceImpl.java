package id.ac.ui.cs.advprog.heymart.service;
import id.ac.ui.cs.advprog.heymart.model.Balance;
import id.ac.ui.cs.advprog.heymart.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalanceServiceImpl implements BalanceService{

    @Autowired
    private BalanceRepository balanceRepository;

    @Override
    public Balance add(Balance balance) {
        balanceRepository.add(balance);
        return balance;
    }

    @Override
    public Balance findById(Long id){
        return balanceRepository.findById(id);
    }
    @Override
    public double topUp(Long id, double amount) {    
        return balanceRepository.incrementBalance(id, amount);  
    }

    @Override
    public double withdraw(Long id, double amount) {
        return balanceRepository.decrementBalance(id, amount);
    }

    @Override
    public boolean checkoutUpdate() {
        // TODO: Add update balance logic after checkout
        return false;
    }
}
