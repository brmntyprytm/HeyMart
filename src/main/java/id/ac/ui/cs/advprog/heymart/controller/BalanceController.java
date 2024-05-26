package id.ac.ui.cs.advprog.heymart.controller;
import id.ac.ui.cs.advprog.heymart.model.Balance;
import id.ac.ui.cs.advprog.heymart.model.Supermarket;
import id.ac.ui.cs.advprog.heymart.model.User;
import id.ac.ui.cs.advprog.heymart.repository.SupermarketRepository;
import id.ac.ui.cs.advprog.heymart.repository.UserRepository;
import id.ac.ui.cs.advprog.heymart.service.BalanceService;
import id.ac.ui.cs.advprog.heymart.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

    @GetMapping("/userbalance")
    public String userBalancePage(@ModelAttribute(name = "username") String username, @ModelAttribute( name = "role") String role, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        username = (String) session.getAttribute("username");
        role = (String) session.getAttribute("role");
        User user = userRepository.findByUsername(username);
        model.addAttribute("user", user);
        model.addAttribute("role", role);

        return "userbalance";
    }



    @GetMapping("userbalance/topup")
    public String topUpBalancePage(@ModelAttribute(name = "username") String username, @ModelAttribute( name = "role") String role, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        username = (String) session.getAttribute("username");
        role = (String) session.getAttribute("role");
        User user = userRepository.findByUsername(username);
        model.addAttribute("role", role);
        model.addAttribute("user", user);

        return "topup";
    }

    @PostMapping("userbalance/topup")
    public String topUpBalance(@ModelAttribute(name = "username") String username, @ModelAttribute(name = "role") String role, @ModelAttribute User user, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession();
        username = (String) session.getAttribute("username");
        role = (String) session.getAttribute("role");

        double amount = user.getBalance();

        if (amount <= 0) {
            redirectAttributes.addFlashAttribute("error", "Please enter a valid positive number.");
            return "redirect:/userbalance/topup";
        }

        balanceService.topUp(username, amount);

        return "redirect:/userbalance/topup";
    }

    @GetMapping("/shopbalance")
    public String shopBalancePage(@ModelAttribute(name = "username") String username, @ModelAttribute(name = "role") String role, @ModelAttribute User user, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        username = (String) session.getAttribute("username");
        role = (String) session.getAttribute("role");

        if (role.equals("manager")) {
            User user1 = userRepository.findByUsername(username);
            Long shopId = supermarketRepository.selectSupermarketIdByManager(user1.getId());
            Supermarket supermarket = supermarketRepository.findById(shopId)
                    .orElseThrow(() -> new RuntimeException("Supermarket not found with id: " + shopId));
            model.addAttribute("supermarket", supermarket);
            return "shopbalance";

        } else if (role.equals("admin")) {
            return "redirect:adminHome";
        }

        else{
            return "redirect:listProductUser";
        }
    }

    @GetMapping("shopbalance/withdraw")
    public String withdrawBalancePage(@ModelAttribute(name = "username") String username, @ModelAttribute(name = "role") String role, @ModelAttribute User user, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        username = (String) session.getAttribute("username");
        role = (String) session.getAttribute("role");

        if (role.equals("manager")) {
            User user1 = userRepository.findByUsername(username);
            Long shopId = supermarketRepository.selectSupermarketIdByManager(user1.getId());
            Supermarket supermarket = supermarketRepository.findById(shopId)
                    .orElseThrow(() -> new RuntimeException("Supermarket not found with id: " + shopId));
            model.addAttribute("supermarket", supermarket);
            return "shopwithdraw";

        } else if (role.equals("admin")) {
            return "redirect:adminHome";
        }

        else{
            return "redirect:listProductUser";
        }
    }

    @PostMapping("shopbalance/withdraw")
    public String withdrawBalance(@ModelAttribute(name = "username") String username, @ModelAttribute(name = "role") String role, @ModelAttribute Supermarket supermarket, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        HttpSession session = request.getSession();
        username = (String) session.getAttribute("username");
        role = (String) session.getAttribute("role");

        User user1 = userRepository.findByUsername(username);
        Long shopId = supermarketRepository.selectSupermarketIdByManager(user1.getId());

        double amount = supermarket.getBalance();
        Supermarket currentSupermarket = supermarketRepository.findById(shopId)
                .orElseThrow(() -> new RuntimeException("Supermarket not found with id: " + shopId));
        double currentBalance = currentSupermarket.getBalance();

        if (amount <= 0) {
            redirectAttributes.addFlashAttribute("error", "Please enter a valid positive number.");
            return "redirect:/shopbalance/withdraw";
        } else if (currentBalance - amount < 0) {
            redirectAttributes.addFlashAttribute("error", "Withdrawal amount exceeds the current balance.");
            return "redirect:/shopbalance/withdraw";
        }

        balanceService.withdraw(shopId, amount);
        return "redirect:/shopbalance/withdraw";
    }

}
