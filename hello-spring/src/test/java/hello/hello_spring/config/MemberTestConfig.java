package hello.hello_spring.config;

import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.service.MemberService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@Profile("member")
public class MemberTestConfig {

  private final MemberRepository memberRepository;

  @Autowired
  public MemberTestConfig(MemberRepository memberRepository) {
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
