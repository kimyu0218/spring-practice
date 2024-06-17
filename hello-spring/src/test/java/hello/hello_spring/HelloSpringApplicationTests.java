package hello.hello_spring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@ContextConfiguration(classes = SpringTestConfig.class)
class HelloSpringApplicationTests {

  @Test
  void contextLoads() {
  }
}
