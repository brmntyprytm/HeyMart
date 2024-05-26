package id.ac.ui.cs.advprog.heymart.repository;

import id.ac.ui.cs.advprog.heymart.model.Supermarket;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SupermarketRepository extends JpaRepository<Supermarket, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Supermarket s WHERE s.id = :id")
    void deleteSupermarketById(@Param("id")Long id);
}
