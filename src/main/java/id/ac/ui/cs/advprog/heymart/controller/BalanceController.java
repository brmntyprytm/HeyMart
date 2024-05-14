package id.ac.ui.cs.advprog.heymart.controller;
import id.ac.ui.cs.advprog.heymart.model.Balance;
import id.ac.ui.cs.advprog.heymart.model.Supermarket;
import id.ac.ui.cs.advprog.heymart.model.User;
import id.ac.ui.cs.advprog.heymart.repository.SupermarketRepository;
import id.ac.ui.cs.advprog.heymart.repository.UserRepository;
import id.ac.ui.cs.advprog.heymart.service.BalanceService;
import id.ac.ui.cs.advprog.heymart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;

@Controller
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SupermarketRepository supermarketRepository;

    @GetMapping("/userbalance/{username}")
    public String userBalancePage(@PathVariable(name = "username") String username, Model model) {

        User user = userRepository.findByUsername(username);
        model.addAttribute("user", user);


        return "userbalance";
    }



    @GetMapping("userbalance/topup/{username}")
    public String topUpBalancePage(@PathVariable(name = "username") String username, Model model) {
        User user = userRepository.findByUsername(username);

        model.addAttribute("user", user);

        return "topup";
    }

    @PostMapping("userbalance/topup/{username}")
    public String topUpBalance(@PathVariable(name = "username") String username, @ModelAttribute User user, Model model) {
        balanceService.topUp(username, user.getBalance());

        return "redirect:/userbalance/topup/{username}";
    }

    @GetMapping("shopbalance/{shopId}")
    public String shopBalancePage(@PathVariable(name= "shopId") Long shopId, Model model) {
        Supermarket supermarket = supermarketRepository.findById(shopId)
                .orElseThrow(() -> new RuntimeException("Supermarket not found with id: " + shopId));
        model.addAttribute("supermarket", supermarket);
        return "shopbalance";
    }

    @GetMapping("shopbalance/withdraw/{shopId}")
    public String withdrawBalancePage(@PathVariable(name = "shopId") Long shopId, Model model) {
        Supermarket supermarket = supermarketRepository.findById(shopId)
                .orElseThrow(() -> new RuntimeException("Supermarket not found with id: " + shopId));
        model.addAttribute("supermarket", supermarket);
        return "shopwithdraw";
    }

    @PostMapping("shopbalance/withdraw/{shopId}")
    public String withdrawBalance(@PathVariable(name  = "shopId") Long shopId, @ModelAttribute Supermarket supermarket, Model model) {
        balanceService.withdraw(shopId, supermarket.getBalance());
        return "redirect:/shopbalance/withdraw/{shopId}";
    }
}
