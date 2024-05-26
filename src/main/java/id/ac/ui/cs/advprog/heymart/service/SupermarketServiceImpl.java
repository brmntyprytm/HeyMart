package id.ac.ui.cs.advprog.heymart.service;

import id.ac.ui.cs.advprog.heymart.model.Supermarket;
import id.ac.ui.cs.advprog.heymart.repository.SupermarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupermarketServiceImpl implements SupermarketService {

    private final SupermarketRepository supermarketRepository;

    @Autowired
    public SupermarketServiceImpl(SupermarketRepository supermarketRepository) {
        this.supermarketRepository = supermarketRepository;
    }

    @Override
    public List<Supermarket> getAllSupermarkets() {
        return supermarketRepository.findAll();
    }

    @Override
    public void addSupermarket(Supermarket supermarket) {
        supermarketRepository.save(supermarket);
    }

    @Override
    public void updateSupermarket(Long id, Supermarket updatedSupermarket) {
        if (supermarketRepository.existsById(id)) {
            updatedSupermarket.setId(id);
            supermarketRepository.save(updatedSupermarket);
        }
    }

    @Override
    public void deleteSupermarket(Long id) {
        supermarketRepository.deleteById(id);
    }
}
