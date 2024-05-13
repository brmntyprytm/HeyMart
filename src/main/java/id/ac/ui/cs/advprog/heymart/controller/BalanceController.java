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
}
