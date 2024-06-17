package hello.hello_spring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(properties = "spring.config.location=classpath:application-test.properties")
@ContextConfiguration(classes = SpringTestConfig.class)
class HelloSpringApplicationTests {

  @Test
  void contextLoads() {
  }

//  @TestConfiguration
//  static class SpringTestConfig {
//
//    private final MemberRepository memberRepository;
//
//    @Autowired
//    public SpringTestConfig(MemberRepository memberRepository) {
//      this.memberRepository = memberRepository;
//    }
//
//    @Bean
//    public MemberService memberService() {
//      return new MemberService(memberRepository);
//    }
//  }
}
