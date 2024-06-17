package hello.hello_spring;

import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@TestConfiguration
@Profile("test")
public class SpringTestConfig {

  private final MemberRepository memberRepository;

  @Autowired
  public SpringTestConfig(MemberRepository memberRepository) {
    System.out.println("This is SpringTestConfig");
    this.memberRepository = memberRepository;
  }

  @Bean
  public MemberService memberService() {
    return new MemberService(memberRepository);
  }
}
