package id.ac.ui.cs.advprog.heymart;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class HeyMartApplicationTests {

    private final HeyMartApplication heyMartApplication = new HeyMartApplication();

    @Test
    void contextLoads() {
        assertNotNull(heyMartApplication);
    }

}
