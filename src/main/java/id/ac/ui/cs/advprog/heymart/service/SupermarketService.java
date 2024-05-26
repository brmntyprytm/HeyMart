package id.ac.ui.cs.advprog.heymart.service;

import id.ac.ui.cs.advprog.heymart.model.Supermarket;

import java.util.List;

public interface SupermarketService {

    List<Supermarket> getAllSupermarkets();

    void addSupermarket(Supermarket supermarket);

    void updateSupermarket(Long id, Supermarket updatedSupermarket);

    Supermarket getSupermarketByID(Long id);

    void deleteSupermarket(Long id);
}
