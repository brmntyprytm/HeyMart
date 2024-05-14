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
    public double topUp(String username, double amount) {
        System.out.println("Service topup executed");
        return balanceRepository.incrementUserBalance(username, amount);
    }

    @Override
    public double withdraw(Long id, double amount) {
        return balanceRepository.decrementShopBalance(id, amount);
    }

    @Override
    public boolean checkoutUpdate(String username, Long shopId, double amount) {
        balanceRepository.incrementShopBalance(shopId, amount);
        balanceRepository.decrementUserBalance(username,amount);
        return true;
    }
}
