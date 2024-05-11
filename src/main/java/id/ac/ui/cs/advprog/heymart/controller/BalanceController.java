package id.ac.ui.cs.advprog.heymart.controller;
import id.ac.ui.cs.advprog.heymart.model.Balance;
import id.ac.ui.cs.advprog.heymart.model.User;
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

    @GetMapping("/userbalance/{username}")
    public String userBalancePage(@PathVariable(name = "username") String username, Model model) {
//        if (userId.endsWith("L")) {
//            userId = userId.substring(0, userId.length() - 1);
//        }


//        Long userIdLong = Long.parseLong(userId);
//        Optional<User> userOptional = Optional.ofNullable(userRepository.findById(userIdLong));
//        if (userOptional.isPresent()) {
//            User user = userOptional.get();
//            model.addAttribute("user", user);
//        } else {
//
//        }
        User user = userRepository.findByUsername(username);
        model.addAttribute("user", user);


        return "userbalance";
    }



//    @GetMapping("userbalance/topup/{userId}")
//    public String topUpBalancePage(@PathVariable(name = "userId") String userId, Model model) {
//        if (userId.endsWith("L")) {
//            userId = userId.substring(0, userId.length() - 1);
//        }
//        Balance balance1 = new Balance(1L, 727, "Buyer");
//        balanceService.add(balance1);
//        Balance balance2 = balanceService.findById(Long.parseLong(userId));
//        User user = new User(1L, "a", "b", "c", "d");
//
//        model.addAttribute("user", user);
//        model.addAttribute("balance" , balance2);
//        return "topup";
//    }
//
//    @PostMapping("userbalance/topup/{userId}")
//    public String topUpBalance(@PathVariable(name = "userId") String userId, @ModelAttribute Balance balance, Model model) {
//        if (userId.endsWith("L")) {
//            userId = userId.substring(0, userId.length() - 1);
//        }
//        balanceService.topUp(Long.parseLong(userId), 1.0);
//        return "redirect:/userbalance/topup/{userId}";
//    }
}
