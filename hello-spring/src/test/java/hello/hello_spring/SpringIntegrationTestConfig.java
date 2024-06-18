package hello.hello_spring;

import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.service.MemberService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@TestConfiguration
@Profile("integration")
public class SpringIntegrationTestConfig {

  private final MemberRepository memberRepository;

  @Autowired
  public SpringIntegrationTestConfig(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @PostConstruct
  void postConstruct() {
    System.out.println("POST CONSTRUCT : " + this.getClass().getName());
  }

  @Bean
  public MemberService memberService() {
    return new MemberService(memberRepository);
  }
}
