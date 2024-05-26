package id.ac.ui.cs.advprog.heymart.controller;

import id.ac.ui.cs.advprog.heymart.model.Supermarket;
import id.ac.ui.cs.advprog.heymart.service.SupermarketService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class SupermarketController {

    private final SupermarketService supermarketService;

    @Autowired
    public SupermarketController(SupermarketService supermarketService) {
        this.supermarketService = supermarketService;
    }

    @GetMapping("/supermarkets")
    public String getAllSupermarkets(Model model) {
        model.addAttribute("supermarkets", supermarketService.getAllSupermarkets());
        return "registerSupermarket";
    }

    @GetMapping("/manage")
    public String manageSupermarkets(Model model) {
        List<Supermarket> supermarkets = supermarketService.getAllSupermarkets();
        model.addAttribute("supermarkets", supermarkets);
        return "manageSupermarkets";
    }

    @PostMapping("/supermarkets")
    public String addSupermarket(@ModelAttribute Supermarket supermarket) {
        if (supermarket.getBalance() == null) {
            supermarket.setBalance(0.0);
        }
        supermarketService.addSupermarket(supermarket);
        return "redirect:/adminHome";
    }

    @GetMapping("/edit/{id}")
    public String editSupermarket(@PathVariable Long id, Model model) {
        Supermarket supermarket = supermarketService.getSupermarketByID(id);
        if (supermarket != null) {
            model.addAttribute("supermarket", supermarket);
            return "editSupermarket";
        } else {
            return "redirect:/admin/supermarkets/manage";
        }
    }

    @PostMapping("/delete")
    public String deleteSupermarket(@RequestParam Long id) {
        supermarketService.deleteSupermarket(id);
        return "redirect:/admin/manage";
    }
}
