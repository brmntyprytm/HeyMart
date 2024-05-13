package id.ac.ui.cs.advprog.heymart.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import id.ac.ui.cs.advprog.heymart.model.User;
import id.ac.ui.cs.advprog.heymart.repository.BalanceRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class BalanceServiceImplTest {
    
        @Mock
        private BalanceRepository balanceRepository;

        @InjectMocks
        private BalanceServiceImpl balanceService;
}
