package id.ac.ui.cs.advprog.heymart.controller;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

}
