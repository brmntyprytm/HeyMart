package id.ac.ui.cs.advprog.heymart.service;

import id.ac.ui.cs.advprog.heymart.model.Supermarket;
import id.ac.ui.cs.advprog.heymart.repository.SupermarketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SupermarketServiceTest {

    @Mock
    private SupermarketRepository supermarketRepository;

    @InjectMocks
    private SupermarketServiceImpl supermarketServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllSupermarkets() {
        // Setup
        List<Supermarket> supermarkets = new ArrayList<>();
        when(supermarketRepository.findAll()).thenReturn(supermarkets);

        // Execute
        List<Supermarket> result = supermarketServiceImpl.getAllSupermarkets();

        // Verify
        assertEquals(supermarkets, result);
    }

    @Test
    void testAddSupermarket() {
        // Setup
        Supermarket supermarket = new Supermarket();

        // Execute
        supermarketServiceImpl.addSupermarket(supermarket);

        // Verify
        verify(supermarketRepository).save(supermarket);
    }

    @Test
    void testUpdateSupermarket() {
        // Setup
        Long id = 1L;
        Supermarket updatedSupermarket = new Supermarket();
        when(supermarketRepository.existsById(id)).thenReturn(true);

        // Execute
        supermarketServiceImpl.updateSupermarket(id, updatedSupermarket);

        // Verify
        verify(supermarketRepository).save(updatedSupermarket);
    }

    @Test
    void testGetSupermarketByID() {
        // Setup
        Long id = 1L;
        Supermarket supermarket = new Supermarket();
        when(supermarketRepository.findById(id)).thenReturn(java.util.Optional.of(supermarket));

        // Execute
        Supermarket result = supermarketServiceImpl.getSupermarketByID(id);

        // Verify
        assertEquals(supermarket, result);
    }

    @Test
    void testDeleteSupermarket() {
        // Setup
        Long id = 1L;

        // Execute
        supermarketServiceImpl.deleteSupermarket(id);

        // Verify
        verify(supermarketRepository).deleteSupermarketById(id);
    }

}
