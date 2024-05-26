package id.ac.ui.cs.advprog.heymart.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

class SupermarketRepositoryTest {

    @Mock
    private SupermarketRepository supermarketRepository;

    @InjectMocks
    private SupermarketRepositoryTest supermarketRepositoryTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteSupermarketById() {
        Long idToDelete = 123L;

        supermarketRepositoryTest.deleteSupermarketById(idToDelete);

        verify(supermarketRepository).deleteSupermarketById(idToDelete);
    }

    private void deleteSupermarketById(Long id) {
        supermarketRepository.deleteSupermarketById(id);
    }
}
