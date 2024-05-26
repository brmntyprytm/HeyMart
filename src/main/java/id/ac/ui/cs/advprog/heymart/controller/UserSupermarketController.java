package id.ac.ui.cs.advprog.heymart.controller;

import id.ac.ui.cs.advprog.heymart.model.Supermarket;
import id.ac.ui.cs.advprog.heymart.service.SupermarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserSupermarketController {

    private final SupermarketService supermarketService;

    @Autowired
    public UserSupermarketController(SupermarketService supermarketService) {
        this.supermarketService = supermarketService;
    }

    @GetMapping("/view")
    public String manageSupermarkets(Model model) {
        List<Supermarket> supermarkets = supermarketService.getAllSupermarkets();
        model.addAttribute("supermarkets", supermarkets);
        return "userSupermarketView";
    }
}
