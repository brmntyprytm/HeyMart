package id.ac.ui.cs.advprog.heymart.controller;

import id.ac.ui.cs.advprog.heymart.model.Supermarket;
import id.ac.ui.cs.advprog.heymart.service.SupermarketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SupermarketControllerTest {

    @Mock
    private SupermarketService supermarketService;

    @Mock
    private Model model;

    @InjectMocks
    private SupermarketController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllSupermarkets() {
        List<Supermarket> supermarkets = new ArrayList<>();
        when(supermarketService.getAllSupermarkets()).thenReturn(supermarkets);

        String viewName = controller.getAllSupermarkets(model);

        assertEquals("registerSupermarket", viewName);
        verify(model).addAttribute("supermarkets", supermarkets);
    }

    @Test
    void testManageSupermarkets() {
        List<Supermarket> supermarkets = new ArrayList<>();
        when(supermarketService.getAllSupermarkets()).thenReturn(supermarkets);

        String viewName = controller.manageSupermarkets(model);

        assertEquals("manageSupermarkets", viewName);
        verify(model).addAttribute("supermarkets", supermarkets);
    }

    @Test
    void testAddSupermarket() {
        Supermarket supermarket = new Supermarket();
        doNothing().when(supermarketService).addSupermarket(supermarket);

        String redirectUrl = controller.addSupermarket(supermarket);

        assertEquals("redirect:/adminHome", redirectUrl);
        verify(supermarketService).addSupermarket(supermarket);
    }

    @Test
    void testEditSupermarketForm() {
        Long id = 1L;
        Supermarket supermarket = new Supermarket();
        when(supermarketService.getSupermarketByID(id)).thenReturn(supermarket);

        String viewName = controller.editSupermarketForm(id, model);

        assertEquals("editSupermarket", viewName);
        verify(model).addAttribute("supermarket", supermarket);
    }

    @Test
    void testEditSupermarket() {
        Supermarket updatedSupermarket = new Supermarket();
        updatedSupermarket.setId(1L);
        Supermarket existingSupermarket = new Supermarket();
        existingSupermarket.setId(1L);
        when(supermarketService.getSupermarketByID(updatedSupermarket.getId())).thenReturn(existingSupermarket);

        String redirectUrl = controller.editSupermarket(updatedSupermarket);

        assertEquals("redirect:/admin/manage", redirectUrl);
        verify(supermarketService).updateSupermarket(existingSupermarket.getId(), existingSupermarket);
    }

    @Test
    void testDeleteSupermarket() {
        Long id = 1L;

        String redirectUrl = controller.deleteSupermarket(id);

        assertEquals("redirect:/admin/manage", redirectUrl);
        verify(supermarketService).deleteSupermarket(id);
    }
}
