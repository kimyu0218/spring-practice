package hello.hello_spring;

import hello.hello_spring.config.SpringIntegrationTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = SpringIntegrationTestConfig.class, properties = "spring.config.location=classpath:application-test.properties")
@ActiveProfiles("integration")
class HelloSpringApplicationTests {

  @Test
  void contextLoads() {
  }
}
