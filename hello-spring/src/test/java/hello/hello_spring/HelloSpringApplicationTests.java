package hello.hello_spring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SpringTestConfig.class, properties = "spring.config.location=classpath:application-test.properties")
//@ActiveProfiles("test")
class HelloSpringApplicationTests {

  @Test
  void contextLoads() {
  }
}
