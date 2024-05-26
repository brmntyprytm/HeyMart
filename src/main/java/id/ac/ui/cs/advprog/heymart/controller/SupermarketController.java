package id.ac.ui.cs.advprog.heymart.controller;

import id.ac.ui.cs.advprog.heymart.model.Supermarket;
import id.ac.ui.cs.advprog.heymart.service.SupermarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "supermarkets";
    }

    @PostMapping("/supermarkets")
    public String addSupermarket(@ModelAttribute Supermarket supermarket) {
        supermarketService.addSupermarket(supermarket);
        return "redirect:/admin/supermarkets";
    }

    @PutMapping("/supermarkets/{id}")
    public String updateSupermarket(@PathVariable Long id, @ModelAttribute Supermarket updatedSupermarket) {
        supermarketService.updateSupermarket(id, updatedSupermarket);
        return "redirect:/admin/supermarkets";
    }

    @DeleteMapping("/supermarkets/{id}")
    public String deleteSupermarket(@PathVariable Long id) {
        supermarketService.deleteSupermarket(id);
        return "redirect:/admin/supermarkets";
    }
}
