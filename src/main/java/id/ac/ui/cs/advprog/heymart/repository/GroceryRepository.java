package id.ac.ui.cs.advprog.heymart.repository;

import id.ac.ui.cs.advprog.heymart.model.Grocery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceryRepository extends JpaRepository<Grocery, Long> {
    Grocery findByName(String name);
    boolean existsByName(String name);
}