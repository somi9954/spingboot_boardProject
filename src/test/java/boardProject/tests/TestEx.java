package boardProject.tests;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.TestPropertySources;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.yml")
public class TestEx {
    @Test
    void test1() {

    }
}
