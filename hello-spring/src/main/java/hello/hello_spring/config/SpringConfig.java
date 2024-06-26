package hello.hello_spring.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(MemberConfig.class)
public class SpringConfig {

  @PostConstruct
  void postConstruct() {
    System.out.println("POST CONSTRUCT : " + this.getClass().getName());
  }
}
