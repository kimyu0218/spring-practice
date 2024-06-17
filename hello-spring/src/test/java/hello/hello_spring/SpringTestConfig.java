package hello.hello_spring;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Profile;

@TestConfiguration
@Profile("test")
public class SpringTestConfig {

  public SpringTestConfig() {
    System.out.println("This is SpringTestConfig");
  }
}
