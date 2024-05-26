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
        List<Supermarket> supermarkets = new ArrayList<>();
        when(supermarketRepository.findAll()).thenReturn(supermarkets);

        List<Supermarket> result = supermarketServiceImpl.getAllSupermarkets();

        assertEquals(supermarkets, result);
    }

    @Test
    void testAddSupermarket() {
        Supermarket supermarket = new Supermarket();

        supermarketServiceImpl.addSupermarket(supermarket);

        verify(supermarketRepository).save(supermarket);
    }

    @Test
    void testUpdateSupermarket() {
        Long id = 1L;
        Supermarket updatedSupermarket = new Supermarket();
        when(supermarketRepository.existsById(id)).thenReturn(true);

        supermarketServiceImpl.updateSupermarket(id, updatedSupermarket);

        verify(supermarketRepository).save(updatedSupermarket);
    }

    @Test
    void testGetSupermarketByID() {
        Long id = 1L;
        Supermarket supermarket = new Supermarket();
        when(supermarketRepository.findById(id)).thenReturn(java.util.Optional.of(supermarket));

        Supermarket result = supermarketServiceImpl.getSupermarketByID(id);

        assertEquals(supermarket, result);
    }

    @Test
    void testDeleteSupermarket() {
        Long id = 1L;

        supermarketServiceImpl.deleteSupermarket(id);

        verify(supermarketRepository).deleteSupermarketById(id);
    }

}
